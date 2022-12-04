package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class roperstrameytltalbotsdRegEx {

    public static HashMap<String, Integer> extractEmailAddresses(String text)
    {
        HashMap<String, Integer> emailFrequencies = new HashMap<String, Integer>();
        String emailRegex = "[^.][a-z0-9\\!\\#\\$\\%\\&\\*\\+\\-\\/\\=\\?\\^\\_\\'\\{\\|\\}\\~]{1,64}[^.]@[a-z]{1,255}\\.[a-z]{2,3}";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        // doesnt implement itterable :(
        while (urlMatcher.find())
        {
            if(emailFrequencies.get(text.substring(urlMatcher.start(0), urlMatcher.end(0))) == null) {
                emailFrequencies.put(text.substring(urlMatcher.start(0), urlMatcher.end(0)), 1);
            }
            else {
                int currentCount = emailFrequencies.get(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
                emailFrequencies.put(text.substring(urlMatcher.start(0), urlMatcher.end(0)), 1 + currentCount);

            }
        }

        return emailFrequencies;
    }

    public static void main(String[] args) {
        // hard coding the file for speediness.
        String text = "";
        File file = new File("clinton-33727-emails-by-wiki-03.txt");
        try {
            text = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File directory not found");
        }

        HashMap<String, Integer> frequencies = extractEmailAddresses(text);

        System.out.println("Number of email addresses found: " + frequencies.size());
        for(String emailAddress : frequencies.keySet()) {
            System.out.println(emailAddress + " " + frequencies.get((emailAddress)));
        }
    }
}
