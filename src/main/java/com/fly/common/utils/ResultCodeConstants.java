package com.fly.common.utils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 结果状态码
 * Created by xinshidai on 17/9/18.
 */
public class ResultCodeConstants {
    /**
     * 用户名或密码错误
     */
    public static final int USERNAME_OR_PASSWORD_WRONG = 5001;
    /**
     * 账号已被锁定,请联系管理员
     */
    public static final int ACCOUNT_LOCKED = 5002;

    public static final int FILE_EMPTY_ERROR = 5003;

    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("= *(\\d+);");

        String dir = System.getProperty("user.dir");
        File file = new File(dir + "/src/main/java/com/fly/common/utils/ResultCodeConstants.java");


        File out = new File(dir + "/src/main/resources/message.properties");
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {

            if (line.contains("/**")) {
                String msgLine = br.readLine();
                String msg = msgLine.replaceAll("\\*", "").trim();

                String nextLine = br.readLine();
                if (nextLine != null) {
                    nextLine = br.readLine();
                    if (nextLine != null) {

                        Matcher matcher = pattern.matcher(nextLine);
                        if (matcher.find()) {
                            String code = matcher.group(1);
                            String messageLine = code + "=" + msg + "\n";
                            bw.write(messageLine);
                            System.out.print(messageLine);
                        }
                    }
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
