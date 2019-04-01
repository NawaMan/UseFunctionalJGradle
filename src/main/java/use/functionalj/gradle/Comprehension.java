package use.functionalj.gradle;

import static functionalj.function.Func.f;

import functionalj.function.Func2;
import functionalj.result.Result;

public class Comprehension {
    
    public static void main(String[] args) {
        Func2<String, String, String> func = f((s1, s2)->s1+" "+s2);
        
        Result<String> text1 = Result.valueOf("Text1");
        Result<String> text2 = Result.valueOf("Text2");
        System.out.println(func.applyTo(text1, text2));     //  "Result:{ Value: Text1 Text2 }"
    }
    
}
