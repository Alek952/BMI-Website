package business.persistence;

import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

public class BmiMapper {

    private Database database;

    public BmiMapper(Database database) {

        this.database = database;
    }

    public void insertBmiEntry(
            double bmi,
            double height,
            double weight,
            String category,
            String gender,
            int sport_id,
            int user_id,
            List<Integer> hobbyList) throws UserException {

        try (Connection connection = database.connect()) {

            String sql = "INSERT INTO `bmi`.`bmi_entry`" +
                    "(" +
                    "`height`," +
                    "`weight`," +
                    "`category`," +
                    "`bmi`," +
                    "`gender`," +
                    "`sport_id`," +
                    "`user_id`)" +
                    "VALUES (?,?,?,?,?,?,?);";


            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, height);
                ps.setDouble(2, weight);
                ps.setString(3, category);
                ps.setDouble(4, bmi);
                ps.setString(5, gender);
                ps.setInt(6, sport_id);
                ps.setInt(7, user_id);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int bmiEntryid = ids.getInt(1);

                //TODO:
                for (Integer hobbyId : hobbyList)
                {
                 insertIntoLinkHobbyBmiEntry(bmiEntryid, (int)hobbyId);
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {

            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoLinkHobbyBmiEntry(int bmi_Entry_id, int hobbyID) throws UserException {
        try (Connection connection = database.connect()) {

            String sql =
                    "INSERT INTO link_bmi_hobby " +
                            "(`hobby_id`, " +
                            " `bmi_entry_id`) " +
                            "VALUES (?, ?);";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, hobbyID);
                ps.setDouble(2, bmi_Entry_id);
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {

            throw new UserException(ex.getMessage());
        }
    }
}


