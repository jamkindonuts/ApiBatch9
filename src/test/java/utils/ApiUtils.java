package utils;

public class ApiUtils {

    public static String createPetRequestPayLoad(String id, String name, String status){
        return "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"cockapoo\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"www.dogpicture.com/pamuk.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Trained\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+status+"\"\n" +
                "}\n" ;
    }
}
