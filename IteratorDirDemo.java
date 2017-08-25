# IteratorDirDemo
文件目录迭代输出
import java.io.File;
import java.util.ArrayList;

public class IteratorDirDemo {

	public static void main(String[] args) {
		IteratorDirUtils.iteratorDir(new File("d:\\Web_Demo1"));
	}

}

class IteratorDirUtils {
	private static int level = 0;

	// 迭代一个目录结构
	public static void iteratorDir(File dir) {
		if (dir == null || dir.listFiles().length == 0) {
			return;// 空文件夹就不用进去遍历了,直接返回
		} else {
			File[] files = dir.listFiles();
			// 先目录后文件来显示
			files = sortDirFile(files);
			if (files != null) {
				for (File file : files) {
					StringBuilder sb = new StringBuilder();
					if (file.isFile()) {
						sb.append(getTab(level));// \t的个数
						sb.append(file.getName());// 追加文件名
					} else {
						sb.append(getTab(level));// \t的个数
						sb.append(file.getName());// 追加文件名
						sb.append("*");
					}
					System.out.println(sb.toString());
					//判断是目录的话
					if(file.isDirectory()){
						level++;// 层次加1
						iteratorDir(file);//继续遍历目录
						level--;//后退的时候层级减一
					}
				}
			}
		}
	}

	// 要求先输出目录再输出文件
	private static File[] sortDirFile(File[] files) {
		ArrayList<File> list = new ArrayList<>();
		for (File file : files) {
			// 把目录放进去
			if (file.isDirectory()) {
				list.add(file);
			}
		}

		for (File file : files) {
			// 把文件放进去
			if (file.isFile()) {
				list.add(file);
			}
		}

		return list.toArray(new File[list.size()]);// 集合转换成指定数据类型和大小的数组
	}

	// 返回\t的字符串,个数根据目录层次数来定.比如第三层子目录,那么应该在目录名前有3个\t
	// level 就是目录的层次数
	private static String getTab(int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}
}
