import java.text.SimpleDateFormat
import java.util.Date

lazy val compr = taskKey[Unit]("Compress task")

compr := {
    println("packing STARTED ...")
    val d = new Date()
    val dateFmt = new SimpleDateFormat ("yyyy-MM-dd")
    val arcFileName = System.getenv("USERNAME") + "-" + dateFmt.format(d) + ".7z"
    val arcFile = file("D:/Knova") / arcFileName
    val wrkBaseDirStr = "D:/Knova/-";
    val wrkBaseDir = file(wrkBaseDirStr)
    Process("cmd /c 7z.bat a -w -bd -mx=9 -- " + arcFile + " .", wrkBaseDir).run(true).exitValue()
    println("packing DONE.")
}
