package player;
import player.FloodFill;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class ComputerPlayer {
    private static int[][] board;
    private static int[][] boardNeighbours ;
    private static int size;
    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();                              //odczytujemy PING
            System.out.println("PONG");
            size = Integer.parseInt(br.readLine());     //odczytujemy rozmiar jako tekst i konwertujemy do inta
            board = new int[size][size];                //nasza tablica o wczytanym rozmiarze
            boardNeighbours = new int [size][size];
            for (int i = 0; i < size; i++) {            //wypełniamy zerami bo tak
                for (int j = 0; j < size; j++) {
                    board[j][i] = 0;
                    if ((i==0) || (j==0) || (j==(size-1)) || (i==size-1) )
                    {
                        if((i==0 && j==0) || (i==0 && j==size-1) || (i==size-1 && j==0) || (i==size-1 && j==size-1) )
                            boardNeighbours[j][i]=2;
                        else
                            boardNeighbours[j][i]=3;
                    }                    
                    else 
                        boardNeighbours[j][i]=4;
                }
            }
            System.out.println("Tablica wypełniona zerami:");
            printArray(board,size);
            System.out.println();
            System.out.println("Tablica sąsiadów:");
            printArray(boardNeighbours,size);
 
            while(true) {
               String inputText = br.readLine();   
                String zaczynaj = "ZACZYNAJ";
                if(!inputText.equals(zaczynaj)) {      //jak nie to odczytaj ruch przeciwnego cwela
                    int previousx1 = Integer.parseInt(inputText.split(" ")[0])-1;  
                    int previousy1 = Integer.parseInt(inputText.split(" ")[1])-1;
                    int previousx2 = Integer.parseInt(inputText.split(" ")[2])-1;
                    int previousy2 = Integer.parseInt(inputText.split(" ")[3])-1;
                    board[previousx1][previousy1] = 1;
                    board[previousx2][previousy2] = 1;
                    changeNeighbours(previousx1, previousy1, previousx2, previousy2);
                    System.out.println("Przeciwnik zrobił ruch.");
                    System.out.println("Tablica:");
                    printArray(board,size);
                    System.out.println("Tablica sąsiadów:");
                    printArray(boardNeighbours,size);
                }
                int suma_białe=0;
                int suma_czarne=0;
                int max_ruchy=0;
                for (int i = 0; i < size; i++) {            //wypełniamy zerami bo tak
                    for (int j = 0; j < size; j++) {
                        
                        if(board[j][i] == 0 && (boardNeighbours[j][i]>0) ){
                            FloodFill bucketFill = new FloodFill(board);
                            // bucketFill.inspect();
                            bucketFill.fill(j, i, 0, 0);   //ten 3 parametr to na co zmienia
                            // System.out.println();
                            //bucketFill.inspect();
                           // System.out.print(bucketFill.licznik_czarne);
                           suma_białe=suma_białe + bucketFill.licznik_białe;
                        suma_czarne=suma_czarne + bucketFill.licznik_czarne;
                //         System.out.println("Ilość czarnych wynosi:");
                //   System.out.print(suma_czarne);
                //   System.out.println();
                //    System.out.println("Ilość białych wynosi:");
                 //  System.out.print(suma_białe);
                 //  System.out.println();  
                        
          
                         }                        
                    }
                   
                    
                }
                System.out.println("Ilość czarnych wynosi:");
                   System.out.print(suma_czarne);
                   System.out.println();
                   System.out.println("Ilość białych wynosi:");
                   System.out.print(suma_białe);
                   System.out.println();                
                   if(suma_białe<=suma_czarne)
                            max_ruchy=max_ruchy+suma_białe;
                        else
                            max_ruchy=max_ruchy+suma_czarne;
                   int x1=-1;
                   int y1=-1;
                   int x2=-1;
                   int y2=-1;
                   System.out.print("Ilość pozostalych ruchów wynosi: ");
                   System.out.print(max_ruchy);
                   System.out.println();
                   
                   if(max_ruchy%2!=0 && (max_ruchy>=1)){
                        //nie psujemy
                   for (int i = 0; i < size; i++) {              
                       for (int j = 0; j < size; j++) {
                            if((board[i][j]==0) && (boardNeighbours[i][j]>0) )
                            {
                                System.out.println("Jestem w IF");
                             x1=i;
                             y1=j;                  
                             System.out.print("x1=: ");
                             System.out.println(x1);
                             System.out.print("y1=: ");
                             System.out.println(y1);
                                int[] directions = possibleDirections(x1, y1);                               
                                 if(directions[0]==1)            //znaleziono mozliwosc ruchu
                                 {x2=x1;
                                    y2=y1-1;
                                    break;}
                            
                                 if(directions[1]==1)            //znaleziono mozliwosc ruchu
                                 {x2=x1+1;
                                    y2=y1;
                                    break;}
                                 if(directions[2]==1)            //znaleziono mozliwosc ruchu
                                 {x2=x1;
                                    y2=y1+1;
                                    break;}
                                 if(directions[3]==1)            //znaleziono mozliwosc ruchu
                                 { x2=x1-1;
                                    y2=y1;  
                                    break;}
                            }
                            
                        }
                        if((x1!=-1)||(x2!=-1 || (y1!=-1) || (y2!=-1)))
                            break;
                    }                                             
                   }
                   
                   else
                   { //psujemy
                       int number_pom=-1;
                       int pom_x1=-1;
                       int pom_y1=-1;
                       int pom_x2=-1;
                       int pom_y2=-1;
                       
                       for (int i = 0; i < size; i++) {            
                        for (int j = 0; j < size; j++) {
                            if(boardNeighbours[i][j] == 1 && board[i][j]==0)
                            {
                              System.out.println("Jestem w IF");
                             pom_x1=i;
                             pom_y1=j;                  
                             System.out.print("x1=: ");
                             System.out.println(pom_x1);
                             System.out.print("y1=: ");
                             System.out.println(pom_y1);       
                             
                                int[] directions = possibleDirections(pom_x1, pom_y1);                               
                                 if(directions[0]==1)            //znaleziono mozliwosc ruchu
                                 { x1=pom_x1;
                                    y1=pom_y1-1;
                                    number_pom=0; //gora
                                    break;}
                            
                                 if(directions[1]==1)            //znaleziono mozliwosc ruchu
                                 {x1=pom_x1+1;
                                    y1=pom_y1;
                                    number_pom=1; //prawo
                                    break;}
                            
                                 if(directions[2]==1)            //znaleziono mozliwosc ruchu
                                 { x1=pom_x1;
                                    y1=pom_y1+1;
                                    number_pom=2; //dol
                                    break;}
                            
                                 if(directions[3]==1)            //znaleziono mozliwosc ruchu
                                 {x1=pom_x1-1;
                                    y1=pom_y1; 
                                    number_pom=3; //lewo
                                    break;}
                            }
                            else {
                            if((boardNeighbours[i][j] !=0) && (board[i][j]==0))
                            {
                              System.out.println("Jestem w IF2");
                             pom_x1=i;
                             pom_y1=j;                  
                             System.out.print("x1=: ");
                             System.out.println(pom_x1);
                             System.out.print("y1=: ");
                             System.out.println(pom_y1);       
                             
                                int[] directions = possibleDirections(pom_x1, pom_y1);                               
                                 if(directions[0]==1)            //znaleziono mozliwosc ruchu
                                 { x1=pom_x1;
                                    y1=pom_y1-1;
                                    number_pom=0; //gora
                                    break;}
                            
                                 if(directions[1]==1)            //znaleziono mozliwosc ruchu
                                 {x1=pom_x1+1;
                                    y1=pom_y1;
                                    number_pom=1; //prawo
                                    break;}
                            
                                 if(directions[2]==1)            //znaleziono mozliwosc ruchu
                                 { x1=pom_x1;
                                    y1=pom_y1+1;
                                    number_pom=2; //dol
                                    break;}
                            
                                 if(directions[3]==1)            //znaleziono mozliwosc ruchu
                                 {x1=pom_x1-1;
                                    y1=pom_y1; 
                                    number_pom=3; //lewo
                                    break;}
                            }                                                     
                            
                            } //klamra else
                            
                        }
                        if((x1!=-1)||(pom_x1!=-1 || (y1!=-1) || (pom_y1!=-1)))
                            break;
                    } 
                   int[] directions = possibleDirections(x1, y1);
                   if((directions[0]==1) && (number_pom!=2))
                        {            //znaleziono mozliwosc ruchu
                        x2=x1;
                        y2=y1-1;
                        }
                        else
                            {
                             if(directions[1]==1 && (number_pom!=3))
                                {            //znaleziono mozliwosc ruchu
                                x2=x1+1;
                                y2=y1;
                                }
                                else
                                    { 
                                      if(directions[2]==1 && (number_pom!=0)) 
                                      {           //znaleziono mozliwosc ruchu
                                      x2=x1;
                                      y2=y1+1;
                                      }
                                     
                                           else {
                                                 if(directions[3]==1 && (number_pom!=1))            //znaleziono mozliwosc ruchu
                                                 {x2=x1-1;
                                                 y2=y1;}
                                                 else {
                                                  x2=pom_x1;
                                                  y2=pom_y1;
                                                 }
                                                 } 
                               
                                    }                       
                            }                    
                   } 
                board[x1][y1]=1;
                board[x2][y2]=1;
                changeNeighbours(x1, y1, x2, y2);
                System.out.println("Tablica:");
                printArray(board,size);
                    System.out.println("Tablica sąsiadów:");
                printArray(boardNeighbours,size);
                
                x1+=1; //zwiekszenie wartosci o jeden bo inna numeracja
                y1+=1;
                x2+=1;
                y2+=1;
                System.out.println("Zrobiłem ruch na:");
                System.out.println(x1 + " " + y1+ " " + x2+ " "+y2);
                //break;
            }
        }
        catch (Exception e) {
        }
   }
  
    
    public static void printArray(int tab[][],int size){
        for (int i = 0; i < size; i++) {            
                for (int j = 0; j < size; j++) {
                    System.out.print(tab[j][i]);                 
                }
                System.out.println();
            } 
    }
    
    
    public static void changeNeighbours(int x1, int y1, int x2, int y2){
        boardNeighbours[x1][y1]=0;
        boardNeighbours[x2][y2]=0;
        if(y1==y2){
            System.out.println("Równe y:");
            System.out.println();
            if((x1-1)>=0 && (boardNeighbours[x1-1][y1]>0))
            boardNeighbours[x1-1][y1]--;
            if((y1-1)>=0 && (boardNeighbours[x1][y1-1]>0))
            boardNeighbours[x1][y1-1]--;
            if((y2-1)>=0 && (boardNeighbours[x2][y2-1]>0))
            boardNeighbours[x2][y2-1]--;
            if((y2+1)<=size-1 && (boardNeighbours[x2][y2+1]>0))
            boardNeighbours[x2][y2+1]--; 
            if((y1+1)<=size-1 && (boardNeighbours[x1][y1+1]>0))
            boardNeighbours[x1][y1+1]--;
            if((x2+1)<=size-1 && (boardNeighbours[x2+1][y2]>0))
            boardNeighbours[x2+1][y2]--;
        }
        
        if(x1==x2){
            System.out.println("Równe x:");
            System.out.println();
            if((x1-1)>=0 && (boardNeighbours[x1-1][y1]>0))
        boardNeighbours[x1-1][y1]--;
            if((x1+1)<=size-1 && (boardNeighbours[x1+1][y1]>0))
        boardNeighbours[x1+1][y1]--;
            if((x2-1)>=0 && (boardNeighbours[x2-1][y2]>0))
        boardNeighbours[x2-1][y2]--;
            if((y2+1)<=size-1 && (boardNeighbours[x2][y2+1]>0))
        boardNeighbours[x2][y2+1]--; 
            if((y1-1)>=0 && (boardNeighbours[x2][y2-1]>0))
        boardNeighbours[x2][y2-1]--;
            if((x2+1)<=size-1 && (boardNeighbours[x2+1][y2]>0))
        boardNeighbours[x2+1][y2]--;
        }
    }
    
    
    public static int[] possibleDirections(int x, int y) {
        int result[] = new int[5];      //tablica 3elementowa z mozliwymi wynikami kierunków
        result[0] = 0;                      // ustawiamy poczatkowo na false 
        result[1] = 0;
        result[2] = 0;
        result[3] = 0;
        result[4] = 0;
        int quantityOfPossibleDirections=0;
        // Wypełnianie tablicy result[] true jeżeli można postawić drugą część klocka
        try {
            if (board[x][y-1] == 0) 
            {
                result[0] = 1;
                quantityOfPossibleDirections++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            result[0] = 0;
        }

        try {
            if (board[x+1][y] == 0) 
            {
                result[1] = 1;
                quantityOfPossibleDirections++;
            }
                
        } catch (ArrayIndexOutOfBoundsException e) {
            result[1] = 0;
        }

        try {
            if (board[x][y+1] == 0) 
            {
                result[2] = 1;
                quantityOfPossibleDirections++;
            }
                               
        } catch (ArrayIndexOutOfBoundsException e) {
            result[2] = 0;
        }

        try {
            if (board[x-1][y] == 0)
            {
             quantityOfPossibleDirections++;   
             result[3] = 1;
            }
                
               
        } catch (ArrayIndexOutOfBoundsException e) {
            result[3] = 0;
        }
        result[4] = quantityOfPossibleDirections;
        return result;
    }
   
}