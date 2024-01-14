package org.example;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class telefon extends JFrame {

    private JRadioButton GB128;
    private JRadioButton GB256;
    private JRadioButton GB512;

    telefon() {
        JPanel primaLinie = new JPanel();
        primaLinie.setBounds(0, 0, 750, 50);

        JLabel titlu = new JLabel("Formular Telefoane");
        titlu.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaDoi = new JPanel();
        liniaDoi.setBounds(0, 100, 750, 50);
        liniaDoi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel marca = new JLabel("Marca Telefon:");
        marca.setFont(new Font("Dialog", Font.PLAIN, 30));

        JTextField marcaText = new JTextField();
        marcaText.setPreferredSize(new Dimension(300, 40));

        JPanel liniaTrei = new JPanel();
        liniaTrei.setBounds(0, 150, 750, 50);
        liniaTrei.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 0));

        JLabel ram = new JLabel("RAM:");
        ram.setFont(new Font("Dialog", Font.PLAIN, 30));

        JTextField ramText = new JTextField();
        ramText.setPreferredSize(new Dimension(300, 40));

        JPanel liniaPatru = new JPanel();
        liniaPatru.setBounds(0, 250, 750, 50);

        JLabel memorie = new JLabel("Memorie de stocare:");
        memorie.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaCinci = new JPanel();
        liniaCinci.setBounds(0, 300, 750, 50);
        liniaCinci.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

        GB128 = new JRadioButton("128GB");
        GB256 = new JRadioButton("256GB");
        GB512 = new JRadioButton("512GB");
        GB128.setFont(new Font("Dialog", Font.PLAIN, 20));
        GB256.setFont(new Font("Dialog", Font.PLAIN, 20));
        GB512.setFont(new Font("Dialog", Font.PLAIN, 20));

        ButtonGroup group = new ButtonGroup();
        group.add(GB128);
        group.add(GB256);
        group.add(GB512);

        JLabel culoare = new JLabel("Culoare:");
        culoare.setFont(new Font("Dialog", Font.PLAIN, 30));

        JPanel liniaSase = new JPanel();
        liniaSase.setBounds(0, 400, 750, 60);

        String[] culoareAlegere = {"Black", "Rose Gold", "Silver"};
        JComboBox<String> comboBox = new JComboBox<>(culoareAlegere);
        comboBox.setFont(new Font("Dialog", Font.PLAIN, 20));

        JPanel liniaSapte = new JPanel();
        liniaSapte.setBounds(0, 650, 750, 70);

        JButton save = new JButton("SAVE");
        save.setFont(new Font("Dialog", Font.PLAIN, 25));
        JButton cancel = new JButton("CANCEL");
        cancel.setFont(new Font("Dialog", Font.PLAIN, 25));

        // Adăugare ActionListener pentru butonul de salvare
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvareDateJSON(marcaText.getText(), ramText.getText(), getMemorie(), (String) comboBox.getSelectedItem());
            }
        });

        primaLinie.add(titlu);
        liniaDoi.add(marca);
        liniaDoi.add(marcaText);
        liniaTrei.add(ram);
        liniaTrei.add(ramText);
        liniaPatru.add(memorie);
        liniaCinci.add(GB128);
        liniaCinci.add(GB256);
        liniaCinci.add(GB512);
        liniaSase.add(culoare);
        liniaSase.add(comboBox);
        liniaSapte.add(save);
        liniaSapte.add(cancel);
        this.add(liniaDoi);
        this.add(liniaTrei);
        this.add(liniaPatru);
        this.add(liniaCinci);
        this.add(liniaSase);
        this.add(liniaSapte);
        this.setTitle("Formular");
        this.add(primaLinie);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(750, 750);

        this.setVisible(true);
    }

    private String getMemorie() {
        if (GB128.isSelected()) {
            return "128GB";
        } else if (GB256.isSelected()) {
            return "256GB";
        } else if (GB512.isSelected()) {
            return "512GB";
        }
        return "";
    }

    private void salvareDateJSON(String marca, String ram, String memorie, String culoare) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Marca", marca);
        jsonObject.put("Ram", ram);
        jsonObject.put("Memorie", memorie);
        jsonObject.put("Culoare", culoare);

        try (FileWriter fileWriter = new FileWriter("date_telefon.json", true)) {
            fileWriter.write(jsonObject.toJSONString() + "\n");
            fileWriter.flush();
            JOptionPane.showMessageDialog(this, "Datele au fost salvate cu succes!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "A apărut o eroare la salvarea datelor.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}