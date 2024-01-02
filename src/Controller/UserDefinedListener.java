package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.StaticTool;
import View.UserDefinedJDialog;
import View.MainFrame;

// Class use to import input from user
public class UserDefinedListener implements ActionListener {
	UserDefinedJDialog userDefinedJDialog;

	MainFrame mainFrame;

	public UserDefinedListener(UserDefinedJDialog userDefinedJDialog,
			MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.userDefinedJDialog = userDefinedJDialog;

	}

	// Customize import input from user
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == userDefinedJDialog.getbuttonCancel()) {
			userDefinedJDialog.dispose();
			mainFrame.reStartGame();
		} else if (e.getSource() == userDefinedJDialog.getButtonSure()) {
			String highT = userDefinedJDialog.getjTextFieldLong().getText();
			Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
			Matcher matcher = pattern.matcher(highT);
			int row = 0;
			if (!matcher.matches()) {
				userDefinedJDialog.getjLabelMessage()
						.setText("Please Input from 9 to 30 !!!");
				return;
			} else {
				row = Integer.parseInt(highT);
				if (row < 9 || row > 30) {
					userDefinedJDialog.getjLabelMessage().setText(
							"Please Input from 9 to 30 !!!");
					return;
				}

			}
			String colT = userDefinedJDialog.getjTextFieldWide().getText();
			int col = 0;
			try {
				col = Integer.parseInt(colT);
				if (col < 9 || col > 30) {
					userDefinedJDialog.getjLabelMessage().setText(
							"Please Input from 9 to 30 !!!");
					return;
				}
			} catch (Exception e2) {
				userDefinedJDialog.getjLabelMessage().setText(
						"Please Input from 9 to 30 !!!");
				return;
			}

			String mineT = userDefinedJDialog.getjTextFieldBomb().getText();
			int mine = 0;
			try {
				mine = Integer.parseInt(mineT);
				if (mine < 10) {
					mine = 10;
				} else {
					mine = Math.min(mine, StaticTool.allrow * StaticTool.allcol
							* 4 / 5);
				}
			} catch (Exception e3) {
				userDefinedJDialog.getjLabelMessage().setText("Please Input Number!!!");
				return;
			}
			userDefinedJDialog.dispose();
			StaticTool.allrow = row;
			StaticTool.allcol = col;
			StaticTool.allcount = mine;

			// Restart game after apply new customize
			mainFrame.reStartGame();
		}

	}

}
