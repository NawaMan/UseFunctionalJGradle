package use.functionalj.gradle;

import static functionalj.function.Func.f;
import static functionalj.stream.intstream.IntStreamPlus.range;
import static use.functionalj.gradle.Tree.theTree;

import functionalj.types.Choice;
import functionalj.types.Nullable;
import functionalj.types.Self;

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
        	var temp = (Temperature)self.unwrap();
            return temp.match()
                    .celsius   (c -> Temperature.Fahrenheit(c.celsius()*1.8 + 32.0))
                    .fahrenheit(f -> f);
        }
        default Temperature.Celsius toCelsius(Self self) {
            var temp = (Temperature)self.unwrap();
            return temp.match()
                    .celsius   (c -> c)
                    .fahrenheit(f -> Temperature.Celsius((f.fahrenheit() - 32.0)/1.8));
        }
    }
    
    public static void main(String[] args) {
        var tree1 = Tree.Node("2", Tree.Leaf("2"), Tree.Leaf("3"));
        var is2 = theTree.asNode.get().value.thatEquals("2")
              .or(theTree.asLeaf.get().value.thatEquals("2"));
        
        System.out.println(is2.apply(tree1));
        System.out.println(is2.apply(tree1.left()));
        System.out.println(is2.apply(tree1.right()));
        
        System.out.println(range(1, 6).reduce((a,b)->a*b));
        
        var factorial = f((Integer n) -> range(1, n + 1).reduce((a,b)->a*b).orElse(1));
        System.out.println(factorial.apply(5));
    }
    
}
