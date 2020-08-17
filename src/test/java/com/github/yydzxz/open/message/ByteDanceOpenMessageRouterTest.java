package com.github.yydzxz.open.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.yydzxz.common.message.IByteDanceMessageDuplicateChecker;
import com.github.yydzxz.common.redis.IByteDanceRedisOps;
import com.github.yydzxz.open.message.handler.AuthorizedEventHandler;
import com.github.yydzxz.open.message.handler.BadHandler;
import com.github.yydzxz.open.message.handler.LogHandler;
import com.github.yydzxz.open.message.handler.UnauthorizedEventHandler;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ByteDanceOpenMessageRouterTest {

    private IByteDanceRedisOps byteDanceRedisOps;

    private ByteDanceOpenMessageRouter byteDanceOpenMessageRouter;

    private IByteDanceMessageDuplicateChecker messageDuplicateChecker;

    private LogHandler logHandler;

    private AuthorizedEventHandler authorizedEventHandler;

    private UnauthorizedEventHandler unauthorizedEventHandler;

    private BadHandler badHandler;

    @BeforeEach
    void setUp() {
        this.byteDanceRedisOps = mock(IByteDanceRedisOps.class);
        this.messageDuplicateChecker = mock(IByteDanceMessageDuplicateChecker.class);
        this.logHandler = new LogHandler();
        this.authorizedEventHandler = new AuthorizedEventHandler();
        this.unauthorizedEventHandler = new UnauthorizedEventHandler();
        this.badHandler = new BadHandler();


        this.byteDanceOpenMessageRouter = getByteDanceOpenMessageRouter();

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("正常情况")
    void route() {
        when(messageDuplicateChecker.isDuplicate(anyString())).thenReturn(false);

        ByteDanceOpenMessage message = new ByteDanceOpenMessage();
        message.setEvent("AUTHORIZED");
        message.setAppId("11111111");
        message.setCreateTime(new Date());
        message.setEventTime(new Date());
        ByteDanceOpenMessageResult result = byteDanceOpenMessageRouter.route(message);
        assertEquals(2,result.getResults().size());
        assertEquals("success", result.getDefaultResult());

        when(messageDuplicateChecker.isDuplicate(anyString())).thenReturn(true);
        result = byteDanceOpenMessageRouter.route(message);
        assertEquals(1,result.getResults().size());
        assertEquals("success", result.getDefaultResult());
    }

    @Test
    @DisplayName("当有handler处理时抛出异常")
    void route1() {
        when(messageDuplicateChecker.isDuplicate(anyString())).thenReturn(false);

        ByteDanceOpenMessage message = new ByteDanceOpenMessage();
        message.setEvent("BadHandlerTest");
        message.setAppId("11111111");
        message.setCreateTime(new Date());
        message.setEventTime(new Date());
        ByteDanceOpenMessageResult result = byteDanceOpenMessageRouter.route(message);
        assertEquals(3,result.getResults().size());
        assertEquals(1,result.getExceptions().size());
        assertEquals("handler 处理 message异常", result.getExceptions().get(badHandler).getMessage());
        assertEquals("failed", result.getDefaultResult());
    }

    /**
     * LogHandler#repeatable返回true，所以重复推送时，LogHandler还是会运行，所以result.getResults().size()为1,就是LogHandler的处理结果
     */
    @Test
    @DisplayName("事件重复推送")
    void route2(){
        when(messageDuplicateChecker.isDuplicate(anyString())).thenReturn(true);

        ByteDanceOpenMessage message = new ByteDanceOpenMessage();
        message.setEvent("AUTHORIZED");
        message.setAppId("11111111");
        message.setCreateTime(new Date());
        message.setEventTime(new Date());
        ByteDanceOpenMessageResult result = byteDanceOpenMessageRouter.route(message);
        assertEquals(1,result.getResults().size());
        assertEquals(0,result.getExceptions().size());
        assertEquals("success", result.getDefaultResult());

    }


    private ByteDanceOpenMessageRouter getByteDanceOpenMessageRouter(){
        final ByteDanceOpenMessageRouter router = new ByteDanceOpenMessageRouter(messageDuplicateChecker);
        //注意，到底是要用event还是msgType，或者同时使用来匹配handler
        router.rule()
            .event(ByteDanceOpenMessage.EVENT_UNAUTHORIZED)
            .addHandler(unauthorizedEventHandler)
            .addHandler(logHandler)
            .end();

        router.rule()
            .event(ByteDanceOpenMessage.EVENT_AUTHORIZED)
            .addHandler(logHandler)
            .addHandler(authorizedEventHandler)
            .end();

        router.rule()
            .event("BadHandlerTest")
            .addHandler(logHandler)
            .addHandler(authorizedEventHandler)
            .addHandler(unauthorizedEventHandler)
            .addHandler(badHandler)
            .end();

        return router;
    }
}