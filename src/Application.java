import util.CPF;

public class Application {
    public static void main(String[] args) throws Exception {
      Scanner sc = new Scanner(System.in);

      System.out.println(" __     __    _ _     _           _                  _         ____ ____  _____ \r\n" + //
              " \\ \\   / /_ _| (_) __| | __ _  __| | ___  _ __    __| | ___   / ___|  _ \\|  ___|\r\n" + //
              "  \\ \\ / / _` | | |/ _` |/ _` |/ _` |/ _ \\| '__|  / _` |/ _ \\ | |   | |_) | |_   \r\n" + //
              "   \\ V / (_| | | | (_| | (_| | (_| | (_) | |    | (_| |  __/ | |___|  __/|  _|  \r\n" + //
              "    \\_/ \\__,_|_|_|\\__,_|\\__,_|\\__,_|\\___/|_|     \\__,_|\\___|  \\____|_|   |_|    \r\n" + //
              "                                                                                ");
      
              System.out.println("████████████████████████████▓▒▒░░░ Vai na Web ░░░▒▒▓████████████████████████████\n");

      System.out.print("Informe um CPF:");
      
      String textCpf = sc.nextLine();
      
      CPF cpf = new CPF(textCpf);
      
      if (cpf.isValid()) {
          System.out.printf("O CPF %s é válido.\n", cpf.toString());
      }else{
          System.out.printf("O CPF %s é invalido.\n", cpf.toString());
      }

      sc.close();
    }
}
