package use.functionalj.gradle;

import static functionalj.function.Func.f;
import static functionalj.stream.StreamPlus.range;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static use.functionalj.gradle.Tree.theTree;

import functionalj.types.Choice;
import functionalj.types.Nullable;
import functionalj.types.Type;
import functionalj.types.choice.Self;
import functionalj.types.choice.generator.model.Case;
import functionalj.types.choice.generator.model.CaseParam;
import functionalj.types.choice.generator.model.SourceSpec;
import lombok.val;

public class TryChoice {
    
    @Choice
    interface MayBeSpec<T> {
        void Just(T data);
        void Nothing();
    }
    
    @Choice
    interface EitherSpec<TR, TL> {
        void Left(TR rightData);
        void Right(TL leftData);
    }
    
    @Choice
    interface TrySpec<T> {
        void Success(T data);
        void Problem(Exception problem);
    }
    
    @Choice
    interface LinkedListSpec {
        void Nill();
        void Node(Object value, LinkedList rest);
    }
    
    @Choice
    interface TreeSpec {
        void Node(Object value, Tree left, Tree right);
        void Leaf(Object value);
    }
    
    @Choice
    interface LoginStatusSpec {
        void Login(@Nullable String userName);
        void Logout();
    }
    
    @Choice
    interface ReqestResultSpec<T> {
        void Success(T data);
        void Error(String errorMessage);
        void ConnectionFailed(Exception problem);
    }
    
    @Choice
    interface CommandSpec {
        void Forward(int distance);
        void Backward(int distance);
        void Turn(int angle);
        void Explode();
    }
    
    @Choice
    static interface TemperatureSpec {
        void Celsius(double celsius);
        void Fahrenheit(double fahrenheit);
        
        default Temperature.Fahrenheit toFahrenheit(Self self) {
            Temperature temp = self.unwrap();
            return temp.match()
                    .celsius   (c -> Temperature.Fahrenheit(c.celsius()*1.8 + 32.0))
                    .fahrenheit(f -> f);
        }
        default Temperature.Celsius toCelsius(Self self) {
            Temperature temp = self.unwrap();
            return temp.match()
                    .celsius   (c -> c)
                    .fahrenheit(f -> Temperature.Celsius((f.fahrenheit() - 32.0)/1.8));
        }
    }
    
    public static final SourceSpec spec = new SourceSpec(
            "Command",
            new Type("use.functionalj.gradle", "TryChoice", "CommandSpec", emptyList()),
            "spec",
            false,
            null,
            emptyList(),
            asList(
                new Case("Forward", null, asList(
                        new CaseParam("distance", new Type(null, null, "int", emptyList()), true, null))),
                new Case("Backward", null, asList(
                        new CaseParam("distance", new Type(null, null, "int", emptyList()), true, null))), 
                new Case("Turn", null, asList(
                        new CaseParam("angle", new Type(null, null, "int", emptyList()), true, null))), 
                new Case("Explode", null, emptyList())), 
            emptyList(), 
            emptyList());
    
    public static void main(String[] args) {
        val tree1 = Tree.Node("2", Tree.Leaf("2"), Tree.Leaf("3"));
        val is2 = theTree.asNode.get().value.thatEquals("2")
              .or(theTree.asLeaf.get().value.thatEquals("2"));
        
        System.out.println(is2.apply(tree1));
        System.out.println(is2.apply(tree1.left()));
        System.out.println(is2.apply(tree1.right()));
        
        System.out.println(range(1, 6).reduce((a,b)->a*b));
        
        val factorial = f((Integer n) -> range(1, n + 1).reduce((a,b)->a*b).orElse(1));
        System.out.println(factorial.apply(5));
    }
    
}
