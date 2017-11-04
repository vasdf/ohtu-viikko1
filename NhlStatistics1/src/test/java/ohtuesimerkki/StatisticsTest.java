
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
            
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void pelaajanHakuToimii(){
        Player player = stats.search("Kurri");
        
        assertEquals("Kurri", player.getName());
    }
    
    @Test
    public void eiOlemassaOlevanPelaajanHaku(){
        Player player = stats.search("asdf");
        
        assertEquals(null, player);
    }
    
    @Test
    public void joukkueenPelaajienHakuToimii(){
        List<Player> players = stats.team("EDM");
        
        ArrayList players2 = new ArrayList<>();
        players2.add("Semenko");
        players2.add("Kurri");
        players2.add("Gretzky");
        
        for (Player player :players) {
            assertTrue(players2.contains(player.getName()));
        }
        
        assertTrue(players.size()==players2.size()); 
    }
    
    @Test
    public void enitenPisteitaKeranneetHaku(){
        List<Player> players = stats.topScorers(3);
        
        ArrayList players2 = new ArrayList<>();
        players2.add("Gretzky");
        players2.add("Lemieux");
        players2.add("Yzerman");
        
        assertEquals(players2.get(0), players.get(0).getName());
        assertEquals(players2.get(1), players.get(1).getName());
        assertEquals(players2.get(2), players.get(2).getName());
        assertEquals(players2.size(), players.size());
    }
}
