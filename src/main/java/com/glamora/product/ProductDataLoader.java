package com.glamora.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductDataLoader implements CommandLineRunner {
    private final ProductRepository repository;
    public ProductDataLoader(ProductRepository repository) { this.repository = repository; }

    @Override
    public void run(String... args) {
        repository.saveAll(List.of(
            new Product("Golden Bloom Earrings","Earrings",new BigDecimal("499"),"Delicate gold-tone floral earrings for everyday elegance.","https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?auto=format&fit=crop&w=900&q=85","BESTSELLER",20),
            new Product("Pearl Glow Necklace","Necklaces",new BigDecimal("899"),"A timeless pearl-inspired necklace for special moments.","https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?auto=format&fit=crop&w=900&q=85","NEW",15),
            new Product("Everyday Gold Hoops","Earrings",new BigDecimal("349"),"Lightweight hoops that pair beautifully with every outfit.","https://images.unsplash.com/photo-1617038220319-276d3cfab638?auto=format&fit=crop&w=900&q=85","POPULAR",30),
            new Product("Moonlight Bracelet","Bracelets",new BigDecimal("599"),"A minimal bracelet with a soft, polished finish.","https://images.unsplash.com/photo-1611591437281-460bfbe1220a?auto=format&fit=crop&w=900&q=85","",18),
            new Product("Rose Charm Ring","Rings",new BigDecimal("299"),"A petite statement ring with a romantic rose-gold look.","https://images.unsplash.com/photo-1605100804763-247f67b3557e?auto=format&fit=crop&w=900&q=85","UNDER ₹500",25),
            new Product("Classic Mini Handbag","Bags",new BigDecimal("1299"),"A versatile mini bag for brunch, outings, and evenings.","https://images.unsplash.com/photo-1584917865442-de89df76afd3?auto=format&fit=crop&w=900&q=85","",10),
            new Product("Crystal Pendant","Necklaces",new BigDecimal("699"),"A subtle sparkle that makes a thoughtful gift.","https://images.unsplash.com/photo-1602173574767-37ac01994b2a?auto=format&fit=crop&w=900&q=85","GIFT PICK",12),
            new Product("Pearl Hair Clip Set","Accessories",new BigDecimal("249"),"A pretty finishing touch for simple or festive hairstyles.","https://images.unsplash.com/photo-1590736969955-71cc94901144?auto=format&fit=crop&w=900&q=85","VALUE SET",35),
            new Product("Celeste Drop Earrings","Earrings",new BigDecimal("549"),"Elegant drop earrings for dinners and celebrations.","https://images.unsplash.com/photo-1630019852942-f89202989a59?auto=format&fit=crop&w=900&q=85","NEW",18),
            new Product("Golden Orbit Hoops","Earrings",new BigDecimal("399"),"Modern rounded hoops with a polished golden finish.","https://images.unsplash.com/photo-1617038220319-276d3cfab638?auto=format&fit=crop&w=900&q=85","POPULAR",24),
            new Product("Rose Petal Studs","Earrings",new BigDecimal("279"),"Tiny floral studs for everyday styling.","https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?auto=format&fit=crop&w=900&q=85","UNDER ₹300",32),
            new Product("Midnight Sparkle Studs","Earrings",new BigDecimal("459"),"Crystal-look studs that bring a subtle sparkle.","https://images.unsplash.com/photo-1611652022419-a9419f74343d?auto=format&fit=crop&w=900&q=85","GIFT PICK",16),
            new Product("Layered Dainty Chain","Necklaces",new BigDecimal("749"),"A layered chain necklace for effortless outfits.","https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?auto=format&fit=crop&w=900&q=85","BESTSELLER",14),
            new Product("Heart Locket Necklace","Necklaces",new BigDecimal("799"),"A keepsake-inspired locket necklace for gifting.","https://images.unsplash.com/photo-1602173574767-37ac01994b2a?auto=format&fit=crop&w=900&q=85","GIFT PICK",12),
            new Product("Golden Sun Pendant","Necklaces",new BigDecimal("649"),"A sunshine-inspired pendant for everyday wear.","https://images.unsplash.com/photo-1611652022419-a9419f74343d?auto=format&fit=crop&w=900&q=85","NEW",20),
            new Product("Pearl Strand Bracelet","Bracelets",new BigDecimal("449"),"Soft pearl details in a graceful bracelet.","https://images.unsplash.com/photo-1611591437281-460bfbe1220a?auto=format&fit=crop&w=900&q=85","POPULAR",17),
            new Product("Twist Cuff Bracelet","Bracelets",new BigDecimal("529"),"A sculptural cuff bracelet with a clean silhouette.","https://images.unsplash.com/photo-1573408301185-9146fe634ad0?auto=format&fit=crop&w=900&q=85","TRENDING",11),
            new Product("Classic Stack Rings","Rings",new BigDecimal("399"),"A stackable ring set to mix and match.","https://images.unsplash.com/photo-1605100804763-247f67b3557e?auto=format&fit=crop&w=900&q=85","VALUE SET",22),
            new Product("Dainty Heart Ring","Rings",new BigDecimal("329"),"A sweet heart detail with a delicate finish.","https://images.unsplash.com/photo-1605100804763-247f67b3557e?auto=format&fit=crop&w=900&q=85","NEW",19),
            new Product("Vintage Signet Ring","Rings",new BigDecimal("579"),"A vintage-inspired ring that makes a statement.","https://images.unsplash.com/photo-1603561591411-07134e71a2a9?auto=format&fit=crop&w=900&q=85","TRENDING",9),
            new Product("Everyday Crossbody Bag","Bags",new BigDecimal("1499"),"A compact crossbody bag for everyday essentials.","https://images.unsplash.com/photo-1584917865442-de89df76afd3?auto=format&fit=crop&w=900&q=85","BESTSELLER",8),
            new Product("Evening Mini Clutch","Bags",new BigDecimal("1199"),"A chic mini clutch for parties and celebrations.","https://images.unsplash.com/photo-1566150905458-1bf1fc113f0d?auto=format&fit=crop&w=900&q=85","PARTY EDIT",7),
            new Product("Satin Bow Hair Clip","Accessories",new BigDecimal("199"),"A soft bow clip for a simple romantic look.","https://images.unsplash.com/photo-1590736969955-71cc94901144?auto=format&fit=crop&w=900&q=85","UNDER ₹200",40),
            new Product("Floral Claw Clip","Accessories",new BigDecimal("179"),"A pretty claw clip for quick everyday hairstyles.","https://images.unsplash.com/photo-1590736969955-71cc94901144?auto=format&fit=crop&w=900&q=85","VALUE PICK",28),
            new Product("Crystal Hair Pin Set","Accessories",new BigDecimal("299"),"A set of sparkling pins for festive hairstyles.","https://images.unsplash.com/photo-1590736969955-71cc94901144?auto=format&fit=crop&w=900&q=85","VALUE SET",26),
            new Product("Gift Box Duo Set","Gift Sets",new BigDecimal("999"),"A coordinated jewellery gift set for special occasions.","https://images.unsplash.com/photo-1602173574767-37ac01994b2a?auto=format&fit=crop&w=900&q=85","GIFT READY",10),
            new Product("Minimal Watch Bracelet","Bracelets",new BigDecimal("899"),"A sleek bracelet-style accessory with a minimal look.","https://images.unsplash.com/photo-1573408301185-9146fe634ad0?auto=format&fit=crop&w=900&q=85","NEW",9),
            new Product("Amber Bead Necklace","Necklaces",new BigDecimal("599"),"Warm-toned beads for a playful layered style.","https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?auto=format&fit=crop&w=900&q=85","TRENDING",13),
            new Product("Classic Pearl Studs","Earrings",new BigDecimal("299"),"Pearl-look studs that suit both casual and formal wear.","https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?auto=format&fit=crop&w=900&q=85","CLASSIC",27),
            new Product("Velvet Jewellery Pouch","Accessories",new BigDecimal("149"),"A soft pouch to keep favourite pieces organised.","https://images.unsplash.com/photo-1602173574767-37ac01994b2a?auto=format&fit=crop&w=900&q=85","ADD-ON",50)
        ).stream().filter(p -> !repository.existsByNameIgnoreCase(p.getName())).toList());
    }
}
