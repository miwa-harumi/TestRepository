package hello.spring.mvc.echo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EchoForm implements Serializable {
	
	  @NotNull // (1)
	    @Size(min = 1, max = 5) // (2)
	    private String name;

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

}
