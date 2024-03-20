package utils;

public class Tool{
    String Code;
    String Type;
    String Brand;


    
    public Tool(String code, String type, String brand) {
        Code = code;
        Type = type;
        Brand = brand;
    }

    public String getCode() {
        return Code;
    }
    public void setCode(String code) {
        Code = code;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public String getBrand() {
        return Brand;
    }
    public void setBrand(String brand) {
        Brand = brand;
    }
}