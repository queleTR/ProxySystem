package de.quele.proxysystem.utils;

/*
    Created by Andre
    At 10:31 Uhr | 25. März. 2021
    Project BungeeSystem
*/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import javax.imageio.ImageIO;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ImageUtil {
     private HashMap<ChatColor, Color> referenceColors;

     private ImageUtil() {
          this.referenceColors = new HashMap<>();
          this.referenceColors.put(ChatColor.BLACK, new Color(0, 0, 0));
          this.referenceColors.put(ChatColor.DARK_BLUE, new Color(0, 0, 170));
          this.referenceColors.put(ChatColor.DARK_GREEN, new Color(0, 170, 0));
          this.referenceColors.put(ChatColor.DARK_RED, new Color(170, 0, 0));
          this.referenceColors.put(ChatColor.DARK_AQUA, new Color(0, 170, 170));
          this.referenceColors.put(ChatColor.DARK_PURPLE, new Color(170, 0, 170));
          this.referenceColors.put(ChatColor.GOLD, new Color(255, 170, 0));
          this.referenceColors.put(ChatColor.GRAY, new Color(170, 170, 170));
          this.referenceColors.put(ChatColor.DARK_GRAY, new Color(85, 85, 85));
          this.referenceColors.put(ChatColor.BLUE, new Color(85, 85, 255));
          this.referenceColors.put(ChatColor.GREEN, new Color(85, 255, 85));
          this.referenceColors.put(ChatColor.AQUA, new Color(85, 255, 255));
          this.referenceColors.put(ChatColor.RED, new Color(255, 85, 85));
          this.referenceColors.put(ChatColor.LIGHT_PURPLE, new Color(255, 85, 255));
          this.referenceColors.put(ChatColor.YELLOW, new Color(255, 255, 85));
          this.referenceColors.put(ChatColor.WHITE, new Color(255, 255, 255));
     }

     public static ImageUtil createImageUtil() {
          return new ImageUtil();
     }

     public void sendImageMessage(ProxiedPlayer to, String uuid, TextComponent... centeredMessage) throws IOException {
          BufferedImage avatarImage = ImageIO.read(new URL("https://crafatar.com/avatars/" + uuid + ".png"));
          ChatColor[][] colors = getImageColor(avatarImage);
          sendChatMessage(to, colors, centeredMessage);
     }

     private ChatColor[][] getImageColor(BufferedImage image) {
          BufferedImage smallerImage = scale(image);
          ChatColor[][] chatColors = new ChatColor[8][8];
          for (int w = 0; w < smallerImage.getWidth(); w++) {
               for (int h = 0; h < smallerImage.getHeight(); h++) {
                    int rgb = smallerImage.getRGB(w, h);
                    ChatColor chatColor = getChatColor(new Color(rgb, true));
                    chatColors[w][h] = chatColor;
               }
          }
          return chatColors;
     }

     private static BufferedImage scale(BufferedImage src)
     {
          BufferedImage img =
               new BufferedImage(8, 8, BufferedImage.TYPE_INT_RGB);
          int x, y;
          int ww = src.getWidth();
          int hh = src.getHeight();
          int[] ys = new int[8];
          for (y = 0; y < 8; y++)
               ys[y] = y * hh / 8;
          for (x = 0; x < 8; x++) {
               int newX = x * ww / 8;
               for (y = 0; y < 8; y++) {
                    int col = src.getRGB(newX, ys[y]);
                    img.setRGB(x, y, col);
               }
          }
          return img;
     }

     private ChatColor getChatColor(Color colorAtPixel) {
          int r = colorAtPixel.getRed();
          int g = colorAtPixel.getGreen();
          int b = colorAtPixel.getBlue();
          Optional<ChatColor> selected = Optional.empty();
          double bestDistance = -1.0D;
          for (ChatColor chatColor : this.referenceColors.keySet()) {
               int r2 = ((Color)this.referenceColors.get(chatColor)).getRed();
               int differenceRed = r - ((Color)this.referenceColors.get(chatColor)).getRed();
               int differenceGreen = g - ((Color)this.referenceColors.get(chatColor)).getGreen();
               int differenceBlue = b - ((Color)this.referenceColors.get(chatColor)).getBlue();
               double rmean = (r + r2) / 2.0D;
               double weightR = 2.0D + rmean / 256.0D;
               double weightG = 4.0D;
               double weightB = 2.0D + (255.0D - rmean) / 256.0D;
               double resultingDifference = weightR * differenceRed * differenceRed + weightG * differenceGreen * differenceGreen + weightB * differenceBlue * differenceBlue;
               if (resultingDifference < bestDistance || bestDistance == -1.0D) {
                    bestDistance = resultingDifference;
                    selected = Optional.of(chatColor);
               }
          }
          return selected.orElse(ChatColor.BLACK);
     }

     private TextComponent[] toLines(ChatColor[][] colors, TextComponent... centeredMessage) {
          TextComponent[] lines = new TextComponent[8];
          for (int y = 0; y < 8; y++) {
               TextComponent line = new TextComponent("");
               for (int x = 0; x < 8; x++) {
                    ChatColor color = colors[x][y];
                    line.addExtra((color != null) ? (colors[x][y].toString() + '█') : " ");
               }
               if (y == 7)
                    line.addExtra("\n");
               if (centeredMessage.length == 2) {
                    if (y == 3)
                         line.addExtra(centeredMessage[0].getText());
                    if (y == 4)
                         line.addExtra((BaseComponent)centeredMessage[1]);
               }
               lines[y] = line;
          }
          return lines;
     }

     private void sendChatMessage(ProxiedPlayer player, ChatColor[][] colors, TextComponent... centeredMessage) {
          TextComponent[] lines = toLines(colors, centeredMessage);
          for (TextComponent imageLine : lines)
               player.sendMessage((BaseComponent)imageLine);
     }
}
