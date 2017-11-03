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
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaTilavuudellaLuotuVarasto() {
        Varasto varasto3 = new Varasto(-5);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiYlitaVarastoSaldoa() {
        varasto.lisaaVarastoon(20);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanEnemmanKuinSaldo() {
        varasto.lisaaVarastoon(8);
        
        assertEquals(8, varasto.otaVarastosta(20), vertailuTarkkuus);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiTeeMitaan() {
        varasto.lisaaVarastoon(7);
        
        varasto.otaVarastosta(-5);
        
        assertEquals(7, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        
        varasto.lisaaVarastoon(-6);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonToinenKonstruktoriToimii() {
        Varasto v2 = new Varasto(10,5);
        
        assertEquals(5, v2.getSaldo(), vertailuTarkkuus);
        assertEquals(10, v2.getTilavuus(), vertailuTarkkuus);
        
        v2 = new Varasto(2,5);
        
        assertEquals(2, v2.getSaldo(), vertailuTarkkuus);
        
        v2 = new Varasto(10,-5);
        
        assertEquals(0, v2.getSaldo(), vertailuTarkkuus);
        
        v2 = new Varasto(-5,5);
        
        assertEquals(0, v2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOikein() {
        varasto.lisaaVarastoon(3);
        
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
   

}