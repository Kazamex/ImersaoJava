import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradoraFigurinhas {
    public void criar(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/imagem.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar imagem original para novo imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0,null);
        
        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.CYAN);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("GobKiller", 100, novaAltura - 100);

        // escrever a imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
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

    public static void main(String[] args) throws Exception {
        var gerador = new GeradoraFigurinhas();
        gerador.gerarMemeChaCaindo( "Eu", 
                                    "Aula 2", 
                                    "Fazer Figurinha", 
                                    "Fazer Meme", 
                                    "Meme1_Alura");

        gerador.gerarMemeChaCaindo( "", 
                                    "Eu", 
                                    "voce, dois filhos", 
                                    "Um cachorro", 
                                    "Meme1_Musica");

        gerador.gerarMemeChaCaindo( "Eu", 
                                    "Chá", 
                                    "Chão", 
                                    "Bule", 
                                    "Meme1_Embaralhado");

        gerador.gerarMemeChaCaindo( "Texto1", 
                                    "Texto2", 
                                    "Texto3", 
                                    "Texto4", 
                                    "Meme1_Default");
        
    }
}
