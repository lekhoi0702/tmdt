package spring.controller.chung;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.UserTBEntity;
import spring.controller.web.UserController;

@Controller
@javax.transaction.Transactional
public class IconController {
	//String defaulf = "logo_highland.png";
	@Autowired
	ServletContext context;
	@Autowired
	SessionFactory factory;

	

	public UserTBEntity getUser(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where userName = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}


	@RequestMapping(value = "user-avt", method = RequestMethod.POST)
	public String Avt(ModelMap model, @RequestParam("avt") MultipartFile avt, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			String path=avt.getOriginalFilename().trim();
			
			if (path.length()>40) {
				path=path.substring(0, 39);
			}
			String photoPath = context
					.getRealPath("/files/" + user1.getUserName().trim() + path);
		

			String id = user1.getUserName();
			UserTBEntity user = this.getUser(id);
			
			avt.transferTo(new File(photoPath));
			user.setIcon(user.getUserName().trim() + path);
			user1.setIcon(user.getUserName().trim() + path);

			try {
				
				session.update(user);
				t.commit();
				model.addAttribute("message", "cập nhật thành công!");

				Thread.sleep(5000);
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "cập nhật thất bại!");
				e.printStackTrace();
			} finally {
				session.close();
			}

		} catch (Exception e) {
			model.addAttribute("message", "lỗi lưu file!");
			e.printStackTrace();
		}

		return "redirect:user.htm";

	}

}