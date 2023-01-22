package modele;
import utils.Connex;
import java.io.IOException;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class Funcobj {
    private String pfx;
    private int lpfx;
    private String seqfunc;
    public String getPfx() {
        return this.pfx;
    }
    public void setPfx(String pfx) {
        this.pfx = pfx;
    }
    public int getLpfx() {
        return this.lpfx;
    }
    public void setLpfx(int lpfx) {
        this.lpfx = lpfx;
    }
    public String getSeqfunc() {
        return this.seqfunc;
    }
    public void setSeqfunc(String seqfunc) {
        this.seqfunc = seqfunc;
    }

    public Object[] selectMethod(String[] wanted, Connection connection) throws Exception {
        Class classObj = this.getClass();
        int countError = 0;
        String req = "select ";
        if (wanted != null) {
            for (int i = 0; i < wanted.length; i++) {
                if (this.checkAttr(wanted[i]) == -1)
                    countError++;
            }
            if (countError == 0) {
                for (int i = 0; i < wanted.length; i++) {
                    if (i < wanted.length - 1)
                        req += wanted[i].toLowerCase() + ", ";
                    else
                        req += wanted[i].toLowerCase() + " ";
                }
            } else {
                throw new IOException("Les noms de colonnes ne coincident pas.");
            }
        } else {
            req += "* ";
        }
        req += "from " + classObj.getSimpleName().toLowerCase();
        Vector<Field> isNull = this.getFieldIntoVector();
        if (isNull.size() != 0) {
            req += " where ";
            for (int i = 0; i < isNull.size(); i++) {
                Field tmp = (Field) (isNull.get(i));
                Method tmpMethod = classObj.getDeclaredMethod(this.getOrSet(tmp, "get"));
                req += tmp.getName() + "='" + tmpMethod.invoke(this) + "'";
                if (i < isNull.size() - 1)
                    req += " and ";
            }
        }
        Vector<Object> vector = new Vector<Object>();
        java.sql.Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(req);
        while (resultSet.next()) {
            Object object = classObj.newInstance();
            if (wanted == null) {
                Field[] fields = classObj.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Method method = classObj.getDeclaredMethod(this.getOrSet(fields[i], "set"), fields[i].getType());
                    Object tmp = resultSet.getObject(fields[i].getName());
                    method.invoke(object, fields[i].getType().cast(tmp));
                }
            } else {
                for (int i = 0; i < wanted.length; i++) {
                    Field field = classObj.getDeclaredField(wanted[i]);
                    Method method = classObj.getDeclaredMethod(this.getOrSet(field, "set"), field.getType());
                    Object tmp = resultSet.getObject(field.getName());
                    method.invoke(object, field.getType().cast(tmp));
                }
            }
            vector.add(object);
        }
        Object[] retour = new Object[vector.size()];
        vector.copyInto(retour);
        connection.close();
        return retour;
    }
    public void insertMethod( Connection connection,String primaryKey) throws Exception {
        
        connection = Connex.getConnex();
        Vector<Field> vector = this.getFieldIntoVector();
        java.sql.Statement statement = connection.createStatement();
        if (vector.size() == 0)
            throw new IOException("Insertion vide");
        Class class1 = this.getClass();
        if (this.hasId(primaryKey)) {
            Field field = class1.getDeclaredField(primaryKey);
            vector.add(field);
            String reqCount = "select count(*) isa from " + class1.getSimpleName().toLowerCase();
            ResultSet resultSet = statement.executeQuery(reqCount);
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("isa");
            }
            count++;
            Method method = class1.getDeclaredMethod(this.getOrSet(field, "set"), field.getType());
            method.invoke(this, count);
        }
        String request = "insert into " + class1.getSimpleName().toLowerCase() + " (";
        for (int i = 0; i < vector.size(); i++) {
            Field tmp = (Field) vector.get(i);
            request += tmp.getName().toLowerCase();
            if (i < vector.size() - 1)
                request += ", ";
        }
        request += ") values (";
        for (int i = 0; i < vector.size(); i++) {
            Field tmp = (Field) vector.get(i);
            Method method = class1.getDeclaredMethod(this.getOrSet(tmp, "get"));
            request += "'" + method.invoke(this) + "'";
            if (i < vector.size() - 1)
                request += ", ";
        }
        request += ")";
        statement.executeUpdate(request);
        System.out.println(request);
        connection.close();
    }
    public void updateMethod(Connection c,String primaryKey) throws Exception {//Mbola ts vita
        
        c = Connex.getConnex();
        Vector<Field> vector = this.getFieldIntoVector();
        String qry = "UPDATE ";
        Class cl = this.getClass();
        qry += cl.getSimpleName().toLowerCase() + " SET ";
        for (int i = 0; i < vector.size(); i++) {
            Field field = (Field)vector.get(i);
            if (field.getName().toLowerCase().compareToIgnoreCase(primaryKey.toLowerCase()) != 0) {
                Method method = cl.getDeclaredMethod(this.getOrSet(field, "get"));
                qry += field.getName().toLowerCase() + "='" + method.invoke(this) + "'";
                if (i < vector.size() - 1)
                    qry += ",";
            }
        }
        qry += " WHERE " + primaryKey + "=";
        Field field = cl.getDeclaredField(primaryKey);
        Method method = cl.getDeclaredMethod(this.getOrSet(field, "get"));
        qry += method.invoke(this);
        System.out.println(qry);
        Statement st = c.createStatement();
        st.executeUpdate(qry);
        st.close();
        c.close();
    }
    public String getOrSet(Field field, String method) throws Exception {
        String retour = "";
        retour = method+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
        return retour;
    }
    public Vector<Field> getFieldIntoVector() throws Exception {
        Vector<Field> retour = new Vector<Field>();
        Class class1 = this.getClass();
        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Method method = class1.getDeclaredMethod(this.getOrSet(fields[i], "get"));
            String req = String.valueOf(method.invoke(this));
            if (req.compareToIgnoreCase("null") != 0 && req.compareToIgnoreCase("0") != 0 && req.compareToIgnoreCase("0.0") != 0) {
                retour.add(fields[i]);
            }
        }
        return retour;
    }
    public int checkAttr(String s) {
        int retour = -1;
        Class class1 = this.getClass();
        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().toLowerCase().compareToIgnoreCase(s.toLowerCase()) == 0)
                retour = 0;
        }
        return retour;
    }
    public boolean hasId(String primaryKey) {
        Class class1 = this.getClass();
        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().toLowerCase().compareToIgnoreCase(primaryKey.toLowerCase()) == 0) {
                return true;
            }
        }
        return false;
    }
    public String getPK(Connection c) throws Exception {

        c = Connex.getConnex();
        int seqnow = 0;
        String qry = "SELECT "+this.getSeqfunc()+".NEXTVAL FROM DUAL";
        Statement st = c.createStatement();
        ResultSet rt = st.executeQuery(qry);
        while(rt.next()){
            seqnow = rt.getInt("NEXTVAl");
        }
        String seqval = String.valueOf(seqnow);
        String prefixe = this.getPfx();
        int size = prefixe.length()+seqval.length();
        int reste = this.getLpfx() - size;
        String value = this.getPfx();
        for(int i=0;i<reste;i++){
            value = value +"0";
        }
        value = value + seqval;
        c.close();
        return value;
    }
}
