import com.example.MyTgBots.config.BotConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static jdk.javadoc.internal.tool.Main.execute;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();


            switch (update.getMessage().getText()) {
                case "/start":

                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                default:

                        sendMessage(chatId,"Sorry your message is not supported");

                    }


            }
        }

    }

    private void startCommandReceived(long chatId, String firstName) {
        String answer = getAnswer(firstName);

        try {
            sendMessage(chatId, answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

private static String getAnswer(String firstName) {
    return "Hi, " + firstName + ", nice to meet you!";
}

private void sendMessage(long chatId, String textToSend) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        execute(String.valueOf(sendMessage));
    }




public void main() {
}
