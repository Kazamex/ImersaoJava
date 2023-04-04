import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerGenerator {
    public void generate(InputStream inputStream, String fileName, String directory, String itemName) throws Exception{
        //Read image
        BufferedImage originalImage = ImageIO.read(inputStream);
        
        // Create new image in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int extraHeight = 200;
        int newHeight = height + extraHeight;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copy and change original image to new image
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0,null);
        
        // Font config
        int fontSize = 64;
        var font = new Font("Impact", Font.BOLD, fontSize);
        graphics.setColor(Color.CYAN);
        graphics.setFont(font);

        // Writes new line on the new image 
        //Calculate position of the text
        int positionX = (width/2) - ((fontSize * itemName.length())/4);
        int positionY = newHeight - ((extraHeight / 2) - (fontSize / 2));
        graphics.drawString(itemName, positionX  , positionY);

        //Creating a outline
        FontRenderContext fontRender = graphics.getFontRenderContext();
        TextLayout text = new TextLayout(itemName,font, fontRender);
        Shape shape = text.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(positionX, positionY);
        graphics.transform(transform);
        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(5f));
        graphics.draw(shape);
        graphics.setClip(shape);

        // Write the image to a new file
        ImageIO.write(newImage, "png", new File(directory + fileName));
    }

    public void gerarMemeChaCaindo(String garota, String bule, String xicara, String chao, String nomeMeme) throws Exception{
        //Buffered
        InputStream inputStream = new FileInputStream(new File("entrada/meme1.png"));
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        
        //Sizes
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        
        //Nova Imagem
        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TRANSLUCENT);
        
        //Editando
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0,null);
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        graphics.setColor(Color.black);
        
        graphics.setFont(fonte);
        graphics.drawString(garota, 450, 50);
        
        fonte = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        graphics.setFont(fonte);
        graphics.drawString(bule, 180, 50);
        graphics.drawString(xicara, 120, 350);
        graphics.drawString(chao, 120, 900);
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeMeme + ".png"));
    }

    // public static void main(String[] args) throws Exception {
    //     var gerador = new StickerGenerator();
    //     gerador.gerarMemeChaCaindo( "Eu", 
    //                                 "Aula 2", 
    //                                 "Fazer Figurinha", 
    //                                 "Fazer Meme", 
    //                                 "Meme1_Alura");

    //     gerador.gerarMemeChaCaindo( "", 
    //                                 "Eu", 
    //                                 "voce, dois filhos", 
    //                                 "Um cachorro", 
    //                                 "Meme1_Musica");

    //     gerador.gerarMemeChaCaindo( "Eu", 
    //                                 "Chá", 
    //                                 "Chão", 
    //                                 "Bule", 
    //                                 "Meme1_Embaralhado");

    //     gerador.gerarMemeChaCaindo( "Texto1", 
    //                                 "Texto2", 
    //                                 "Texto3", 
    //                                 "Texto4", 
    //                                 "Meme1_Default");
        
    // }
}
