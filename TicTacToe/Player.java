class Player{
    private final String name;
    private final Symbol symbol;
    Player(String name, Symbol symbol){
        this.name = name;
        this.symbol = symbol;
    }
    String getPlayerName(){
        return this.name;
    }
    Symbol getPlayerSymbol(){
        return this.symbol;
    }
}