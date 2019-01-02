package servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import dao.impl.AlbumDaoImpl;
import dao.impl.PicDaoImpl;
import model.Album;
import model.Pic;
import model.User;
import util.DateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
public class UploadPhotoServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    public UploadPhotoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<Album> list = new ArrayList<>();
        AlbumDaoImpl impl = new AlbumDaoImpl();
        list = impl.findByUserId(userId);
        req.setAttribute("list", list);
        RequestDispatcher rd = req.getRequestDispatcher("upload.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean flag = true;
        String photoPath = "";
        String photoName = "";
        SmartUpload smartUpload = new SmartUpload();
        Request inputRequest = null;
        try {
            smartUpload.initialize(getServletConfig(), req, resp);
            smartUpload.setAllowedFilesList("jpg,gif,png,bmp,JPG,GIF,BMP");
            smartUpload.setMaxFileSize(1024000 * 2);
            smartUpload.upload();
            inputRequest = smartUpload.getRequest();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            String username = user.getName();
            String destFilepath = "/home/riddleli/album/group30/" + username + "/"
                    + inputRequest.getParameter("albumId").trim();
            System.out.println(destFilepath);
            if(inputRequest.getParameter("albumId") == null || inputRequest.getParameter("albumId").length() <= 0) {
                req.setAttribute("errorMessage", "需要选择一个相册！");
                req.setAttribute("returnUrl", "../upload");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }
            File myFile = smartUpload.getFiles().getFile(0);
            if (!myFile.isMissing()) {
                photoName = myFile.getFileName();
                if (photoName == null || photoName.length() <= 0){
                    req.setAttribute("errorMessage", "照片名不能为空");
                    req.setAttribute("returnUrl", "../upload");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    return;
                }
                java.io.File file = new java.io.File(destFilepath);
                if(!file.exists()) {
                    flag = file.mkdirs();
                }
                if (!flag){
                    System.out.println("create file failed");
                    req.setAttribute("errorMessage", "文件夹创建失败");
                    req.setAttribute("returnUrl", "../upload");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    return;
                }
                System.out.println("create file succeed");
                photoPath = destFilepath+ "/" + photoName;
                myFile.saveAs(photoPath, SmartUpload.SAVE_PHYSICAL);
                System.out.println("save file succeed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        if(!flag) {
            req.setAttribute("errorMessage", "上传失败");
            req.setAttribute("returnUrl", "../upload");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }
        Pic pic = new Pic();
        pic.setTitle(inputRequest.getParameter("photoTitle").trim());
        pic.setDescription(inputRequest.getParameter("photoDescription").trim());
        pic.setAlbumid(Integer.parseInt(inputRequest.getParameter("albumId")));
        pic.setFilename(photoName);
        pic.setPublishtime(DateUtil.getNowDateTime("YYYY-MM-DD HH:MM:ss"));
        pic.setUrl(photoPath);
        //将对象保存至数据库
        PicDaoImpl impl = new PicDaoImpl();
        boolean add = impl.addPhoto(pic);
        if(add) {
            //添加成功
            resp.sendRedirect("../homepage");
        }
        else {
            //添加失败
            req.setAttribute("errorMessage", "上传失败");
            req.setAttribute("returnUrl", "../upload");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }
    }
}
