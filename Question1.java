public class SalesTaxCalculator {
    public static abstract class TaxCalculator {
        public abstract double compute(double value);
    }
    //for no tax states
    public static class NoTax extends TaxCalculator {
        @Override
        public double compute(double value) {
            return 0.0;
        }
    }    
    // for 7% tax states
    public static class SevenPercent extends TaxCalculator {
        @Override
        public double compute(double value) {
            return value * 0.07;
        }
    }
    public static class FourPFivePercent  extends TaxCalculator {
        @Override
        public double compute(double value) {
            return value * 0.045;
        }
    }

    // Abstract class representing a state
    public static abstract class State {
        protected String name;
        protected TaxCalculator taxCalculator;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void showTax(double value) {
            double tax = taxCalculator.compute(value);
            System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, name, tax);
        }
    }
    //set Alaska to no tax
    public static class Alaska extends State {
        public Alaska() {
            this.name = "Alaska";
            this.taxCalculator = new NoTax(); 
        }
    }
    // set Indiana to state with 7% Tax
    public static class Indiana extends State {
        public Indiana() {
            this.name = "Indiana";
            this.taxCalculator = new SevenPercent(); 
        }
    }

    public static class Hawaii extends State{
        public Hawaii() {
            this.name = "Hawaii";
            this.taxCalculator = new FourPFivePercent(); 
        }  
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // check the imput
        if (args.length != 2) {
            System.out.println("Usage: java SalesTaxCalculator <state> <saleAmount>");
            return;
        }

        
        String stateName = args[0];
        double saleAmount;

        // check the imput value is double or not
        try {
            saleAmount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid sale amount. Please enter a numeric value.");
            return;
        }

        
        State state;
        // switch the imput string to State
        switch (stateName) {
            case "Alaska":
                state = new Alaska();
                break;
            case "Indiana":
                state = new Indiana();
                break;
            case "Hawaii":
                state = new Hawaii();
                break;
            default:
                System.out.println("Unsupported state. Only 'Alaska', 'Hawaii' and 'Indiana' are supported.");
                return;
        }

        // result
        state.showTax(saleAmount);
    }
}

