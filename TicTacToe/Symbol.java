enum SymbolType{
    X,
    O
}
public class Symbol{
    private final SymbolType symbol;
    Symbol(SymbolType symbol){
        this.symbol = symbol;
    }
    SymbolType getSymbol(){
        return this.symbol;
    }
}