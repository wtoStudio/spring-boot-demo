package demo.components;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class AppLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        /*Locale locale = Locale.getDefault();
        String l = request.getParameter("l");
        if(!StringUtils.isEmpty(l)){
            String[] arr = l.split("_");
            locale = new Locale(arr[0], arr[1]);
        }*/
        Locale locale = new Locale("zh", "CN");
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
