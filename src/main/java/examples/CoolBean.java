package examples;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public record CoolBean(String name) {

    public String sig(){
        return Hashing.sha256()
                .hashString(name, StandardCharsets.UTF_8)
                .toString();
    }
}
