package Books_exercises.JavaReceptury.di.spring;

import org.springframework.stereotype.Component;

// BEGIN main
@Component("myModel")
public class SimpleModel implements Model {

    @Override
    public String getMessage() {
        return "To są przykładowe dane";
    }
}
// END main
