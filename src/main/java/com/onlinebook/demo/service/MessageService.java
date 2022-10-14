package com.onlinebook.demo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @apiNote CLIENTGA QAYTARILADIGAN MESSAGE'LAR BILAN ISHLAYDIGAN SERVICE
 * @since 01.12.2021
 */

@Component
@RequiredArgsConstructor
public class MessageService {
    private static MessageSource messageSource;
    @Autowired
    public void setSomeThing(MessageSource messageSource)
    {
        MessageService.messageSource = messageSource;
    }

    /**
     * KEY KELSA USHA KEYGA TEGISHLI BO'LGAN MESSAGE QAYTARADI. <br/>
     * BU KEY-VALUENI oladigan joy <pre>/src/java/resources/messages</pre>
     */
    public static String getMessage(String key) {
        try
        {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        }
        catch (Exception e)
        {
            return key;
        }
    }

    /**
     * IKKITA KEY KELSA ULARNING MESSAGENI
     * {@link String#format(String, Object...)} ORQALI BIRLASHTIRIB QAYTARADI <hr/>
     * MASALAN: YANGI TADBIR MUVAFFAQIYATLI SAQLANDI, KLIENTGA MOS MESSAGE QAYTARISH KK. <br/>
     * SHUNDA BU METODGA "SUCCESS_SAVE" VA "EVENT" KEYLARI KELSA.
     * HAR IKKALA KEYNING VALUESINI {@link #getMessage(String)} DAN OLIB {@link String#format(String, Object...)}
     * ORQALI BIRLASHTIRADI. SHUNDA QUYIDAGI MESSAGE HOSIL BO'LADI: <pre>Tadbir muvaffaqiyatli saqlandi!</pre>
     */
    private static String merge(String action, String sourceKey) {
        return String.format(getMessage(action), getMessage(sourceKey));
    }
}
