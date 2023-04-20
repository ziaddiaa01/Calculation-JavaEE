package service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.CalculationEntity;
import repositry.CalculationRepositry;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Path("/")
public class CalculationService {
    @Inject
    private CalculationRepositry repositry;
    @Path("calc")
    @POST
    public ResponseResult calculate(CalculationEntity calc) {
        int result;
        switch (calc.getOperation()) {
            case "+":
                result = calc.getNumber1() + calc.getNumber2();
                break;
            case "-":
                result = calc.getNumber1() - calc.getNumber2();
                break;
            case "*":
                result = calc.getNumber1() * calc.getNumber2();
                break;
            case "/": {
                if (calc.getNumber2() == 0)
                    throw new IllegalArgumentException("Can't divide by zero");
                result = calc.getNumber1() / calc.getNumber2();
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
        repositry.insert(calc);
        return new ResponseResult(result);
    }

    @Path("calculations")
    @GET
    public List<CalculationEntity> getAllCalculations() {
        return repositry.selectAllCalculations();
    }
}
