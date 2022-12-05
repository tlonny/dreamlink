package dreamlink.overlay.home.credits;

import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.texture.sample.MenuTextureSample;

public class CreditsRow {

    public static final CreditsRow teddy = new CreditsRow(
        MenuTextureSample.creditsAvatarTeddy,
        "Teddy",
        "My son"
    );

    public static final CreditsRow han = new CreditsRow(
        MenuTextureSample.creditsAvatarHan,
        "Han",
        "My wife"
    );

    public static final CreditsRow mum = new CreditsRow(
        MenuTextureSample.creditsAvatarMum,
        "Mum",
        "My mum"
    );

    public static final CreditsRow dad = new CreditsRow(
        MenuTextureSample.creditsAvatarDad,
        "Dad",
        "My dad"
    );


    public static final CreditsRow dan = new CreditsRow(
        MenuTextureSample.creditsAvatarDan,
        "Dan",
        "My tallest best friend"
    );

    public static final CreditsRow ed = new CreditsRow(
        MenuTextureSample.creditsAvatarEd,
        "Ed",
        "My baldest best friend"
    );

    public static final CreditsRow dara = new CreditsRow(
        MenuTextureSample.creditsAvatarDara,
        "Dara",
        "My brownest best friend"
    );

    public static final CreditsRow coco = new CreditsRow(
        MenuTextureSample.creditsAvatarCoco,
        "Coco",
        "My furriest best friend"
    );

    public static final CreditsRow timmy = new CreditsRow(
        MenuTextureSample.creditsAvatarTimmy,
        "Timmy",
        "Me!"
    );
    
    private static final CreditsRow[] creditsRows = new CreditsRow[] {
        teddy,
        han,
        mum,
        dad,
        dan,
        ed,
        dara,
        coco,
        timmy
    };

    public static int getSize() {
        return creditsRows.length;
    }

    public static CreditsRow get(int index) {
        return creditsRows[index];
    }
        
    public final ISpriteTemplate avatar;
    public final String name;
    public final String message;

    public CreditsRow(ISpriteTemplate avatar, String name, String message) {
        this.avatar = avatar;
        this.name = name;
        this.message = message;
    }
}