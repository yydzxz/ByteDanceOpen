package com.github.yydzxz.open.util;

import com.google.common.base.CaseFormat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyidian
 * @date 2020/12/11
 **/
@Slf4j
public class AutoGenerateCodeUtil {

    public static final String REQUEST = "request";

    public static final String RESPONSE = "response";

    static Pattern pattern = Pattern.compile("https://open\\.microapp\\.bytedance\\.com/openapi/([\\S]{2})/");

    public static void main(String[] args) {
//        generate("https://open.microapp.bytedance.com/openapi/v1/microapp/operation/video_application_status", false);
//        generate("https://open.microapp.bytedance.com/openapi/v1/microapp/operation/video_application", true);
//        generate("https://open.microapp.bytedance.com/openapi/v1/microapp/operation/live_application_status", false);
//        generate("https://open.microapp.bytedance.com/openapi/v1/microapp/operation/live_application", true);
//        generate("https://open.microapp.bytedance.com/openapi/v1/microapp/operation/phone_number_application_status", false);
//        generate("https://open.microapp.bytedance.com/openapi/v1/microapp/operation/phone_number_application", true);

        generateRequestClassFields();
    }

    public static void generate(String apiUrl, boolean hasRequestBody){
        String[] tokens = apiUrl.split("/");
        String version = getVersion(apiUrl);
        System.out.println(version);

        String moduleName = tokens[tokens.length - 2];
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, moduleName));

        String urlFieldName = tokens[tokens.length - 1].toUpperCase() + "_URL";
        System.out.println("String " + urlFieldName + " = \"" + apiUrl +"\";") ;

        String methodName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tokens[tokens.length - 1]);

        String responseClassName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, moduleName) + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tokens[tokens.length - 1]) + "Response";

        String requestClassName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, moduleName) + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tokens[tokens.length - 1]) + "Request";
        log.info("还需要生成serialVersionUID");

        String methodSignature = "";
        if(hasRequestBody){
            methodSignature = responseClassName + " " + methodName + "(" + requestClassName + " request);";
        }else{
            methodSignature = responseClassName + " " + methodName + "();";
        }
        System.out.println(methodSignature);

        generateFile(version, RESPONSE, moduleName, responseClassName);
        if(hasRequestBody){
            generateFile(version, REQUEST, moduleName, requestClassName);
        }

    }

    /**
     * @param version v1
     * @param type request response
     * @param module
     * @param className
     * @throws IOException
     */
    private static void generateFile(String version, String type, String module, String className) {
        String path = "src/main/java/com/github/yydzxz/open/api/" + version + "/" + type + "/" + module+ "/" + className + ".java";
        File file = new File(path);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            bufferedWriter.write("package com.github.yydzxz.open.api." + version + "." + type + "." + module + ";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            if(RESPONSE.equals(type)){
                bufferedWriter.write("import com.github.yydzxz.common.error.ByteDanceError;");
                bufferedWriter.newLine();
            }
            if(RESPONSE.equals(type)){
                bufferedWriter.write("import com.github.yydzxz.common.http.IByteDanceResponse;");
                bufferedWriter.newLine();
            }else if(REQUEST.equals(type)){
                bufferedWriter.write("import com.github.yydzxz.common.http.IByteDanceRequest;");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("import lombok.Data;");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write("@Data");
            bufferedWriter.newLine();
            if(RESPONSE.equals(type)){
                bufferedWriter.write("public class " + className + " extends ByteDanceError implements IByteDanceResponse {");
                bufferedWriter.newLine();
            }else if(REQUEST.equals(type)){
                bufferedWriter.write("public class " + className + " implements IByteDanceRequest {");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String getVersion(String apiUrl){
        Matcher matcher = pattern.matcher(apiUrl);
        if (matcher.find( )) {
            return matcher.group(1);
        }else {
            throw new RuntimeException("没匹配到version");
        }
    }

    private static void generateRequestClassFields(){
        List<Map<String, String>> fields = new ArrayList<>();
        Map<String, String> nameAndType = new HashMap<>();
        nameAndType.put("name", "reason");
        nameAndType.put("type", "Integer");
        nameAndType.put("comment", "申请原因。 选项：1或2 1：用于必要登录场景，2：用于收货联系方式");
        fields.add(nameAndType);

        nameAndType = new HashMap<>();
        nameAndType.put("name", "scene");
        nameAndType.put("type", "Integer");
        nameAndType.put("comment", "使用场景。 选项：1～7 1：账号下信息内容同步，2：网络购物，3：票务预订，4：业务办理，5：信息查询（如社保、公积金查询），6：预约，7：其他");
        fields.add(nameAndType);


        nameAndType = new HashMap<>();
        nameAndType.put("name", "description");
        nameAndType.put("type", "String");
        nameAndType.put("comment", "场景描述。字数限制：10~200");
        fields.add(nameAndType);

        nameAndType = new HashMap<>();
        nameAndType.put("name", "imagePaths");
        nameAndType.put("type", "List<String>");
        nameAndType.put("comment", "场景示例图列表。\n"
            + "* 注意：\n"
            + "* 需要使用 上传图片材料 接口返回的路径才可以，否则报错。\n"
            + "* 目前仅支持传入 1 张图片的路径，若有多张场景示例图，请将它们拼接为 1 张图片后上传并在此处传入长度为 1 的场景示例图路径列表。");
        fields.add(nameAndType);

        generateAnnotation(fields);
    }

    private static void generateAnnotation(List<Map<String,String>> fields){
        for (Map<String, String> nameAndType : fields){
            generateAnnotation(nameAndType);
        }
    }


    private static void generateAnnotation(Map<String,String> map){
        System.out.println("/**");
        System.out.println("* " + map.get("comment"));
        System.out.println("*/");
        System.out.println("@JSONField(name = \"" + map.get("name") + "\")");
        System.out.println("@JsonAlias(\"" + map.get("name") + "\")");
        System.out.println("@JsonProperty(\"" + map.get("name") + "\")");
        System.out.println("@SerializedName(\"" + map.get("name") + "\")");
        System.out.println("private " + map.get("type") + " " + map.get("name") + ";");
        System.out.println();
    }

}
