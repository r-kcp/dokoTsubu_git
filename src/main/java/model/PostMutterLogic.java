package model;

import java.util.List;

public class PostMutterLogic {

	public void execute(Mutter mutter, List<Mutter> mutterList) {
		
		mutterList.add(0,mutter); //0番目に追加（挿入）
	}
}
