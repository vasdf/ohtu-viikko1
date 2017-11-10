package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    final int d = 10000;
    double vertailuTarkkuus = 1/d;
    final int c = 10;
    final int a = 5;
    final int b = 8;
    
    @Before
    public void setUp() {
        varasto = new Varasto(c);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(c, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaTilavuudellaLuotuVarasto() {
        Varasto varasto3 = new Varasto(a-c);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(b);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(b, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(b);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiYlitaVarastoSaldoa() {
        varasto.lisaaVarastoon(c*2);
        
        assertEquals(c, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(b);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(b);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(2+2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanEnemmanKuinSaldo() {
        varasto.lisaaVarastoon(b);
        
        assertEquals(b, varasto.otaVarastosta(2*c), vertailuTarkkuus);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiTeeMitaan() {
        varasto.lisaaVarastoon(b-1);
        
        varasto.otaVarastosta(a-c);
        
        assertEquals(b-1, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        
        varasto.lisaaVarastoon(a-c-1);
        
        assertEquals(c, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonToinenKonstruktoriToimii() {
        Varasto v2 = new Varasto(c,a);
        assertEquals(a, v2.getSaldo(), vertailuTarkkuus);
        assertEquals(c, v2.getTilavuus(), vertailuTarkkuus);
        
        v2 = new Varasto(2,a);
        assertEquals(2, v2.getSaldo(), vertailuTarkkuus);
        
        v2 = new Varasto(c,a-c);
        assertEquals(0, v2.getSaldo(), vertailuTarkkuus);
        
        v2 = new Varasto(a-c,a);
        assertEquals(0, v2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOikein() {
        varasto.lisaaVarastoon(2+1);
        
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
   

}