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
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
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
    public void uudenVarastonNegatiivinenTilavuusMuuttuuNollaksi() {
        varasto = new Varasto(-10);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldollinenKonstruktoriPalauttaaOikeanSaldon() {
        varasto = new Varasto(20, 10);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldollinenKonstruktoriPalauttaaOikeanTilavuuden() {
        varasto = new Varasto(20, 10);

        assertEquals(20, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoNegatiivinenTilavuusMuuttuuNollaksi() {
        varasto = new Varasto(-10, 0);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoNegatiivinenSaldoMuuttuuNollaksi() {
        varasto = new Varasto(0, -10);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }    
    
    @Test
    public void negatiivinenLisäysEiVaikutaSaldoon() {
        varasto.lisaaVarastoon(-10);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiVaikutaSaldoon() {
        varasto.otaVarastosta(-10);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void negatiivinenOttoPalauttaaNolla() {

        assertEquals(0, varasto.otaVarastosta(-10), vertailuTarkkuus);
    }
    
    @Test
    public void ylimääräinenLisäysMeneeHukkaan() {
        varasto.lisaaVarastoon(20);

        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimääräinenOttoAntaaKokoSaldon() {
        varasto.lisaaVarastoon(10);

        assertEquals(varasto.otaVarastosta(20), 10, vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimiiOikein() {
        varasto.lisaaVarastoon(10);
        
        assertEquals(varasto.toString(), "saldo = 10.0, vielä tilaa 0.0");
    }
}