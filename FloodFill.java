/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;
public class FloodFill {
    private int[][] pixels;
    public int licznik_czarne=0;
    public int licznik_białe=0;
    public FloodFill(int[][] pixels) {
        this.pixels = pixels;
    }

    public void fill(int x, int y, int newColor, int oldColor) {
        if((x+y)%2==0)
            licznik_czarne++;
        else
            licznik_białe++;
        
        
        if (x < 0) return;
        if (y < 0) return;
        if (x >= pixels.length) return;
        if (y >= pixels[x].length) return;

        int color = pixels[x][y];

        if (newColor == color) return;
        if (oldColor != color) return;

        pixels[x][y] = newColor;   
        fill(x - 1, y, newColor, oldColor);
        fill(x + 1, y, newColor, oldColor);
        fill(x, y - 1, newColor, oldColor);
        fill(x, y + 1, newColor, oldColor);
    }

    public void inspect() {
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                System.out.print(pixels[y][x]);
            }
            System.out.print("\n");
        }
    }

 /*   public static void main(String argv[]) {
        int pixels[][] =
        {
            { 0, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 1 },
            { 1, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 1 },
            { 0, 0, 1, 1, 1 },
            { 1, 0, 1, 1, 1 }
        };
        FloodFill bucketFill = new FloodFill(pixels);
        bucketFill.inspect();
        bucketFill.fill(0, 0, 1, 0);   //ten 3 parametr to na co zmienia
        System.out.println();
        bucketFill.inspect();
        System.out.println();
        System.out.print(licznik);
    } */
}