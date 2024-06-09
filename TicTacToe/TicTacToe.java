import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TicTacToe{
    private final int size;
    private Map<Symbol, Player> playerSymbolMapper;
    private Symbol [][] board;
    private Deque<Player> playersList;

    public TicTacToe(int size){
        this.size = size;
        initializeBoard();
        initializePlayers();
    }

    private void initializeBoard(){
        board = new Symbol[size][size];
        printBoard();
    }

    private void initializePlayers(){
        playerSymbolMapper = new HashMap<>();
        playersList = new ArrayDeque<>();
        Symbol SymbolO = new SymbolO();
        Symbol SymbolX = new SymbolX();
        Player player1 =  new Player("Player1", SymbolO);
        Player player2 =  new Player("Player2", SymbolX);
        playersList.addLast(player1);
        playersList.addLast(player2);
        playerSymbolMapper.put(SymbolO, player1);
        playerSymbolMapper.put(SymbolX, player2);
    }

    private void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null){
                    System.out.print(" . ");
                }
                else{
                    System.out.print(" "+board[i][j].getSymbol()+" ");
                }
            }
            System.out.println("");
        }
    }

    public void startGame(){
        Scanner sc = new Scanner(System.in);
        while(true){
            Player currentPlayer = playersList.removeFirst();
            System.out.println(currentPlayer.getPlayerName()+" turn, enter row and column");

            // taking row and col input
            int row = sc.nextInt();
            int col = sc.nextInt();

            // check if row and col is valid
            if(row<0 || row>=size || col<0 || col>=size){
                System.out.println("Invalid row or col value");
                playersList.addFirst(currentPlayer);
                continue;
            }
            // check if element already present on that position
            if(board[row][col]!=null){
                System.out.println("Position already filled");
                playersList.addFirst(currentPlayer);
                continue;
            }

            board[row][col] = currentPlayer.getPlayerSymbol();
            playersList.addLast(currentPlayer);
            printBoard();
            try {
                if(checkWinnerExists()) break;
            } catch (Exception e) {
               throw new Error("Something went wrong! Please start the game again");
            }
        }
      
    }

    // check it any empty cell is present in the board
    private boolean isBoardContainsFreeCell(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==null) return true;
            }
        }
        return false;
    }

    // check if the array contains all same symbols;
    private boolean checkIfAllElementsSame(Symbol[] row) {
        Symbol initial = row[0];
        if(initial == null) return false;
        for (Symbol symbol : row) {
            if(symbol == null) return false;
            if (symbol.getSymbol() != initial.getSymbol()) return false;
        }
        return true;
    }

    private Symbol[] getColumn(int colIndex) {
        Symbol[] column = new Symbol[size];
        for (int i = 0; i < size; i++) {
            column[i] = board[i][colIndex];
        }
        return column;
    }

    // returning the elements of daigonal stating from [0, 0]
    private Symbol[] getMainDiagonal() {
        Symbol[] diagonal = new Symbol[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = board[i][i];
        }
        return diagonal;
    }

    // returning the elements of daigonal stating from [0, size-1]
    private Symbol[] getSecondaryDiagonal() {
        Symbol[] diagonal = new Symbol[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = board[i][size - 1 - i];
        }
        return diagonal;
    }


    private boolean checkWinnerExists(){

        Symbol winnerSymbol = null;
        // checking rows and column for finding the winner
        for(int i=0;i<size;i++){
           if(checkIfAllElementsSame(board[i])){
            winnerSymbol = board[i][0];
            break;
           }
           else if(checkIfAllElementsSame(getColumn(i))){
            winnerSymbol = board[0][i];
            break;
           }
        }
      
        // checking both diagonals for finding the winner
        if(winnerSymbol==null){
            if(checkIfAllElementsSame(getMainDiagonal())) {
                winnerSymbol = board[0][0];
            }else if(checkIfAllElementsSame(getSecondaryDiagonal())){
                winnerSymbol = board[0][size-1];
            }
        }

        if(winnerSymbol==null){
            // check if empty cells present, then continue the game;
            if(isBoardContainsFreeCell()) return false;

            // If no empty cell is present and also there is no winner then the game is tie
            System.out.println("The game is Tie");
            return true;
        }

        // printing the winner player name, getting from symbol player mapper
        if(playerSymbolMapper.containsKey(winnerSymbol)){
            System.out.println(playerSymbolMapper.get(winnerSymbol).getPlayerName()+" is winner");
            return true;
        }

        return false;
    }
}