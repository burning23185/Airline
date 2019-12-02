package util;

import java.util.ArrayList;

public class PageUtil {

	private int listHight = 10; // 화면 당 표시할 갯수(디폴트)
	private int block = 10; // 리스트 밑에 블록 수(디폴트)
	private int totalPageNum = 0; // 전체 페이지 수

	public PageUtil(int size) {
		this.totalPageNum = getTotalNumOfPage(size);
	}

	public PageUtil(int listHight, int blockNum, int size) {
		this(size);
		setListHight(listHight);
		setBlock(blockNum);
	}

	public int getListHight() {
		return listHight;
	}

	public void setListHight(int listHight) {
		this.listHight = listHight;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getTotalNumOfPage(int size) {
		if (size % listHight == 0)
			return size / listHight;
		return (size / listHight) + 1;
	}

	public int getTotalNumOfBlock() {
		if (totalPageNum % block == 0)
			return totalPageNum / block;
		return (totalPageNum / block) + 1;
	}

	public int getNextBlock(int nowPage) {
		int nextBlock;
		if (nowPage <= block) {
			return block + 1;
		} else if (nowPage % block == 0) {
			nextBlock = (nowPage + 1);
		} else {
			// ex) 23 -> 20+10+1 = 31
			nextBlock = (nowPage - (nowPage % block)) + block + 1;
		}
		if (nextBlock > totalPageNum) {
			nextBlock = totalPageNum + 1;
		}
		return nextBlock;
	}

	public int getPrevBlock(int nowPage) {
		int prevBlock;
		if (nowPage <= block) {
			return 0;
		} else if (nowPage % block == 0) {
			prevBlock = (nowPage - block);
		} else {
			prevBlock = (nowPage - (nowPage % block));
		}
		return prevBlock;
	}

	public ArrayList<Integer> getBlockList(int nowPage) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = getPrevBlock(nowPage) + 1; i < getNextBlock(nowPage); i++) {
			if (i > totalPageNum) {
				break;
			}
			list.add(i);
		}
		return list;
	}

	/*
	 * public static void main(String[] args) { PageUtil pu = new
	 * PageUtil(12,10,13); int nowPage = 1;
	 * System.out.println(pu.getPrevBlock(nowPage));
	 * System.out.println(pu.getNextBlock(nowPage));
	 * System.out.println(pu.getBlockList(nowPage)); }
	 */
}
