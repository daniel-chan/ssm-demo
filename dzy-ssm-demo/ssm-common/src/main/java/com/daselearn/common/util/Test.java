package com.daselearn.common.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Desc:
 *
 * @author daniel
 * @date 2019/7/24
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    private static String getVersion(String groupId,String artifactId) {
        JSONObject jsonObject = new JSONObject();
        try{
            String jarPath = Test.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            jarPath = java.net.URLDecoder.decode(jarPath, "UTF-8");
            //获取jar包的绝对路径
            logger.info("version info : " + jarPath);
            //执行命令的本地路径，如在C:\Users执行 java -jar ,获取到的user.dir就是C:\Users
//                System.out.println(System.getProperty("user.dir") + File.separator);

            URL url = new URL("jar:file:" + jarPath + "!/META-INF/maven/"+groupId+"/"+artifactId+"/pom.properties");
            Properties properties = new Properties();
            properties.load(url.openStream());
            String pomVersion = properties.getProperty("version");
            jsonObject.put("version",pomVersion);
            jsonObject.put("groupId",groupId);
            jsonObject.put("artifactId",artifactId);
        }catch(Exception e){
            logger.error("read jar or pom error:",e);
        }
        return jsonObject.toJSONString();
    }

    public static void main(String[] args) {
        StudentBuilder studentBuilder = new StudentBuilder();
        studentBuilder.setName("zhang");
        studentBuilder.setAge(21);
        studentBuilder.setSchool("z");
        Student student = studentBuilder.build();
        System.out.println("student = " + student.name);
//        System.out.println( getVersion("com.daselearn.ssm","ssm-common"));

        Product stu = new Product();
        if(stu.getName()==null){
            stu.setName("zhangsan1");
        }
        if(stu.getQty()==null){
            stu.setQty(2);
        }

        Optional.ofNullable(stu).map((s)->s.getQty());
//        Optional.of(stu).map((a)->a.school="school");

//        setNullValue(stu.name,"zhangsan", (s) -> stu.name = s  ); //(s -> stu.name = s)
//        setNullValue(stu.school,"zhongxin",(s) -> stu.school = s);
        System.out.println(stu);


//        List<Long> ids = new ArrayList();
//        for (int i = 0; i < 500; i++) {
//            ids.add(Long.parseLong(String.valueOf(i)));
//        }
//        strategy(ids);


        System.out.println("isEqual = " + Integer.valueOf("isEqual"));
    }

    private static void setNullValue(Object obj, String defval, Consumer<String> consumer ){
        if(Objects.isNull(obj)){
            consumer.accept(defval);
        }
    }

    private static int test(final List<Long> ids) {
        final int threadCount = 20;

        try {
            final ExecutorService es = Executors.newFixedThreadPool(threadCount);
            final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            //并发非阻塞队列
            final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<Long>(ids);
            final List<Long> nofinishList = Collections.synchronizedList(new ArrayList<Long>(ids));
            for ( int i = 0;i<threadCount;i++) {
                final int cnt  = i;
                es.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (!queue.isEmpty()) {
                            Long param = queue.poll();
                            System.out.println(Thread.currentThread().getName()+",i = " + cnt +",id = " + param);
                            if(param!=null){
                                try {

                                    //模拟业务处理
                                    Thread.sleep(1000);

                                    synchronized (nofinishList){
                                        nofinishList.remove(param);

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        //使得主线程(main)阻塞直到latch.countDown()为零才继续执行
                        countDownLatch.countDown();
                    }
                });

                System.out.println("threadCount = " + i);
            }


            System.out.println("await");
            countDownLatch.await();


            es.shutdown();
            System.out.println("shutdown");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
