package conversormoedas.modulos;

public class Menu {
    private String menuPrincipal = """
            *****************************************************************
            Seja bem vindo/a ao Conversor de Moedas!
            
            Menu Principal:
            
            1) Dolar => Peso argentino
            2) Peso argentino => Dolar
            3) Dolar => Real brasileiro
            4) Real brasileiro => Dolar
            5) Dolar => Peso colombiano
            6) Peso colombiano => Dolar
            7) Sair
            
            Escolha a opção válida
            *****************************************************************
            """;

    public String getMenuPrincipal() {
        return menuPrincipal;
    }
}
