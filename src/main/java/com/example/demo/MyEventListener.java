package com.example.demo;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyEventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();

        if (event.getAuthor().isBot()) return;
            String regex = "(?iu)\\b((у|[нз]а|(хитро|не)?вз?[ыьъ]|с[ьъ]|(и|ра)[зс]ъ?|(о[тб]|под)[ьъ]?|(.\\B)+?[оаеи])?-?([её]б(?!о[рй])|и[пб][ае][тц]).*?|(н[иеа]|[дп]о|ра[зс]|з?а|с(ме)?|о(т|дно)?|апч)?-?ху([яйиеёю]|ли(?!ган)).*?|(в[зы]|(три|два|четыре)жды|(н|сук)а)?-?бл(я(?!(х|ш[кн]|мб)[ауеыио]).*?|[еэ][дт]ь?)|(ра[сз]|[зн]а|[со]|вы?|п(р[ои]|од)|и[зс]ъ?|[ао]т)?п[иеё]зд.*?|(за)?п[ие]д[аое]?р((ас)?(и(ли)?[нщктл]ь?)?|(о(ч[еи])?)?к|юг)[ауеы]?|манд([ауеы]|ой|[ао]вошь?(е?к[ауе])?|юк(ов|[ауи])?)|муд([аио].*?|е?н([ьюия]|ей))|мля([тд]ь)?|лять|([нз]а|по)х|м[ао]л[ао]фь[яию])\\b";
            int c = 0;
            String[] list = content.split(" ");
            StringBuilder newContent = new StringBuilder();
            for (int i = 0; i < list.length; i++) {
                if (list[i].matches(regex)) {
                    c++;
                    newContent.append(replaceAll(list[i]));
                    newContent.append(" ");
                } else if (fileReader(list[i])) {
                    c++;
                    newContent.append(replaceAll(list[i]));
                    newContent.append(" ");
                } else {
                    newContent.append(list[i]);
                    newContent.append(" ");
                }
            }
            content = "**" + newContent + "**";
            if (c != 0) {
                AuditableRestAction<Void> auditableRestAction = message.delete();
                auditableRestAction.queue();
                channel.sendMessage(event.getAuthor().getName() + ": \n" + content).queue();
            }
//        }

    }

    private boolean fileReader(String word) {
        String fileName = "./words.txt";
        String line = null;
        try {
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\\,", "");
                String[] words = line.split(" ");
                if (word.length() > 2) {
                    for (int i = 0; i < words.length; i++) {
                        if(words[i].equals(word)) {
                            return true;
                        }
                    }
//                    return line.matches("\\s" + line.contentEquals(word) + "\\s");
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return false;
    }

    private String replaceAll(String word) {
        StringBuilder result = new StringBuilder();
        if (word.length() > 2) {
            result.append(word.charAt(0));
            for (int i = 1; i < word.length() - 1; i++) {
                result.append("#");
            }
            result.append(word.charAt(word.length() - 1));
        } else {
            result.append(word);
        }
        return result.toString();
    }

    @Override
    public void onMessageUpdate(@Nonnull MessageUpdateEvent event) {
        System.out.println("onMessageUpdate");
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        channel.sendMessage(content).queue();
    }

    @Override
    public void onMessageDelete(@Nonnull MessageDeleteEvent event) {
        System.out.println("Message was deleted");
    }

}
