import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCalculoUmidadeSolo {

    private WebDriver driver;
    private  DSL dsl;

    @Before
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("https://calculoumidadesolo.hyggedigitaltec.repl.co/");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeTextoCalculoDaUmidadeDoSolo() {
        Assert.assertEquals("Cálculo da Umidade do Solo", dsl.obterTexto(By.tagName("h1")));
    }

    @Test
    public void testeDeveVerificarValoresComboSoloFino() {
        Assert.assertEquals(3, dsl.obterQuantidadeOpcoesCombo("umidadeSolo1"));
    }

    @Test
    public void testeDeveVerificarValoresComboSoloMedio() {
        Assert.assertEquals(3, dsl.obterQuantidadeOpcoesCombo("umidadeSolo2"));
    }

    @Test
    public void testeDeveVerificarValoresComboSoloGrosseiro() {
        Assert.assertEquals(3, dsl.obterQuantidadeOpcoesCombo("umidadeSolo3"));
    }

    @Test
    public void testeDeveSelecionarValoresCombosSoloFino() {
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo1", "Abaixo de 60"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo1", "60 a 80"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo1", "80 a 100"));
    }

    @Test
    public void testeDeveSelecionarValoresCombosSoloMedio() {
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo2", "Abaixo de 70"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo2", "70 a 88"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo2", "88 a 100"));
    }

    @Test
    public void testeDeveSelecionarValoresCombosSoloGrosseiro() {
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo3", "Abaixo de 80"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo3", "80 a 90"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("umidadeSolo3", "90 a 100"));
    }

    @Test
    public void testeAlertSoloFinoAbaixode60() {
        dsl.selecionarCombo("umidadeSolo1", "Abaixo de 60");
        Assert.assertEquals("Abaixo de 60", dsl.obterValorCombo("umidadeSolo1"));

        dsl.clicarBotao("soloFino");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está perigosamente baixa.\nA irrigação deve ser aplicada imediatamente.", texto);
    }

    @Test
    public void testeAlertSoloFino60a80() {
        dsl.selecionarCombo("umidadeSolo1", "60 a 80");
        Assert.assertEquals("60 a 80", dsl.obterValorCombo("umidadeSolo1"));

        dsl.clicarBotao("soloFino");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está baixa.\nA irrigação deve ser aplicada.", texto);
    }

    @Test
    public void testeAlertSoloFino80a100() {
        dsl.selecionarCombo("umidadeSolo1", "80 a 100");
        Assert.assertEquals("80 a 100", dsl.obterValorCombo("umidadeSolo1"));

        dsl.clicarBotao("soloFino");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está ideal.\nA irrigação não é necessária.", texto);
    }

    @Test
    public void testeAlertSoloMedioAbaixode70() {
        dsl.selecionarCombo("umidadeSolo2", "Abaixo de 70");
        Assert.assertEquals("Abaixo de 70", dsl.obterValorCombo("umidadeSolo2"));

        dsl.clicarBotao("soloMedio");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está perigosamente baixa.\nA irrigação deve ser aplicada imediatamente.", texto);
    }

    @Test
    public void testeAlertSoloMedio70a88() {
        dsl.selecionarCombo("umidadeSolo2", "70 a 88");
        Assert.assertEquals("70 a 88", dsl.obterValorCombo("umidadeSolo2"));

        dsl.clicarBotao("soloMedio");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está baixa.\nA irrigação deve ser aplicada.", texto);
    }

    @Test
    public void testeAlertSoloMedio88a100() {
        dsl.selecionarCombo("umidadeSolo2", "88 a 100");
        Assert.assertEquals("88 a 100", dsl.obterValorCombo("umidadeSolo2"));

        dsl.clicarBotao("soloMedio");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está ideal.\nA irrigação não é necessária.", texto);
    }

    @Test
    public void testeAlertSoloGrosseiroAbaixode80() {
        dsl.selecionarCombo("umidadeSolo3", "Abaixo de 80");
        Assert.assertEquals("Abaixo de 80", dsl.obterValorCombo("umidadeSolo3"));

        dsl.clicarBotao("soloGrosseiro");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está perigosamente baixa.\nA irrigação deve ser aplicada imediatamente.", texto);
    }

    @Test
    public void testeAlertSoloGrosseiro80a90() {
        dsl.selecionarCombo("umidadeSolo3", "80 a 90");
        Assert.assertEquals("80 a 90", dsl.obterValorCombo("umidadeSolo3"));

        dsl.clicarBotao("soloGrosseiro");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está baixa.\nA irrigação deve ser aplicada.", texto);
    }

    @Test
    public void testeAlertSoloGrosseiro90a100() {
        dsl.selecionarCombo("umidadeSolo3", "90 a 100");
        Assert.assertEquals("90 a 100", dsl.obterValorCombo("umidadeSolo3"));

        dsl.clicarBotao("soloGrosseiro");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("A umidade do solo está ideal.\nA irrigação não é necessária.", texto);
    }
}
