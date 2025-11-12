    import org.example.config.DatabaseConnection;
    import org.example.model.Equipamento;
    import org.example.service.impl.EquipamentoServiceImpl;
    import org.junit.jupiter.api.*;

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.SQLOutput;
    import java.sql.Statement;
    import static org.junit.jupiter.api.Assertions.*;

    @DisplayName("Teste Equipamento Service - Prova Prática de Programação API")
    public class EquipamentoServiceTest {

        EquipamentoServiceImpl equipamentoService = new EquipamentoServiceImpl();

        private static final String CREATE_TABLE = """
                CREATE TABLE IF NOT EXISTS Equipamento (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    numeroDeSerie VARCHAR(100) NOT NULL UNIQUE,
                    areaSetor VARCHAR(100) NOT NULL,
                    statusOperacional VARCHAR(50) NOT NULL,
                    CONSTRAINT chk_status_equipamento CHECK (statusOperacional IN ('OPERACIONAL', 'EM_MANUTENCAO', 'INATIVO'))
                );
                """;

        private static final String DROP_TABLE = """
                DROP TABLE IF EXISTS Equipamento;
                """;

        private static final String TRUNCATE_TABLE = """
                TRUNCATE TABLE Equipamento;
                """;

        @BeforeAll
        static void setupDatabase() throws SQLException {
            try (Connection conn = DatabaseConnection.getConenction()) {
                Statement stmt = conn.createStatement();

                stmt.execute(DROP_TABLE);
                System.out.println("Tabela Usuário deletada com sucesso!!!");
                stmt.execute(CREATE_TABLE);
                System.out.println("Tabela Usuário criada com sucesso!!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @AfterAll
        static void tearDownDatabase() {
            try (Connection conn = DatabaseConnection.getConenction()) {
                Statement stmt = conn.createStatement();
                stmt.execute(DROP_TABLE);
                System.out.println("Tabela Usuário deletada com sucesso!!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @BeforeEach
        void clearData() throws SQLException {
            try (Connection conn = DatabaseConnection.getConenction()) {
                Statement stmt = conn.createStatement();
                stmt.execute(TRUNCATE_TABLE);
                System.out.println("Dados limpados com sucesso!!!");
            }

        }

        @Test
        @DisplayName("Retorna o ID do equipamento se o Equipamento for salvo no banco")
        void salvarEquipamentoTest() throws SQLException {

            Equipamento equipamento = new Equipamento("Makita 2025", "61720", "Ferramentaria", "Operacional");
            equipamento  = equipamentoService.criarEquipamento(equipamento);
            assertEquals(1L, equipamento.getId());

        }

        @Test
        @DisplayName("Retorna true se o Usuário foi deletado com sucesso!!!")
        void BuscarEquipamentoPorID() throws SQLException {

            Equipamento equipamento = new Equipamento("Martelo", "342325", "Ferramentaria", "Operacional");
            equipamentoService.criarEquipamento(equipamento);
            var equipamentoBuscado = equipamentoService.buscarEquipamentoPorId(1);

            assertEquals(1, equipamentoBuscado.getId());

        }

        @Test
        @DisplayName("No name")
        void deletarEquipamentoTest() throws SQLException{

            Equipamento e = new Equipamento("Monitor 144hz", "15763", "Tecnologia da Informação", "Operacional");
            equipamentoService.criarEquipamento(e);

            boolean deletar = equipamentoService.deletarEquipamento(e.getNumeroDeSerie());
            assertTrue(deletar);

        }

        @Test
        @DisplayName("Atualiza credenciais de dados de usuário")
        void editarDados(){

        }

    }