package demo.controller;

import demo.annotation.AppLog;
import demo.entity.Article;
import demo.entity.Hoby;
import demo.entity.Person;
import demo.entity.User;
import demo.exception.AppException;
import demo.mapper.HobyMapper;
import demo.mapper.PersonMapper;
import demo.repository.*;
import demo.service.ArticleService;
import demo.service.BaseService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HobyRepository hobyRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private HobyMapper hobyMapper;

    @AppLog(opeaSysName = "WIN10")
    @RequestMapping("/d1/{id}")
    public String demo1(@PathVariable("id") Integer id){
        return "id: " + id;
    }

    @RequestMapping("/d2")
    public User demo2(@Valid User user, BindingResult bindingResult){
        for(FieldError error: bindingResult.getFieldErrors()){
            System.out.println(error.getField() + ":" + error.getDefaultMessage());
        };
        return user;
    }

    @RequestMapping("/add")
    public String demo2(User user){
        baseService.save(user);
        return "ok";
    }

    @RequestMapping("/testUpdate")
    public String testUpdate(){
        userService.updateUserName("wty", "wt");
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/findSomeArticle/{authorId}")
    public List<Article> findSomeArticle(@PathVariable("authorId")Long authorId){
        List<Article> articles = articleRepository.findSomeArticle(authorId);
        System.out.println("articles:\n" + articles);
        return articles;
    }

    @ResponseBody
    @RequestMapping("/updateSomeArticleTitle/{authorId}/{title}")
    public String updateSomeArticleTitle(@PathVariable("authorId")Long authorId, @PathVariable("title")String title){
        articleService.updateSomeArticleTitle(authorId, title);
        return "ok";
    }

    @GetMapping("/auth/list")
    public Map<String, Object> success(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        map.put("user", session.getAttribute("user"));

        List<Article> articles = articleRepository.findSomeArticle(1L);
        map.put("articles", articles);

        return map;
    }

    /*@GetMapping("/auth/list")
    public List<Article> success(HttpSession session){

        List<Article> articles = articleRepository.findSomeArticle(1L);

        return articles;
    }*/

    @RequestMapping("/error")
    public String error(Map<String, Object> map){
        map.put("errorMsg", "<i style='color: red;'>error: 500. There Is a RuntimeException in the Server Side! please fix it!</i>");
        return "error";
    }

    @ResponseBody
    @RequestMapping("/testExceptionHandler/{t}")
    public String testExceptionHandler(@PathVariable("t") Integer t){
        if(t.intValue()==0){
            throw new AppException("appException", new Date(), "hahah");
        }else if(t.intValue()==1){
            throw new RuntimeException("异常啦");
        }
        return "好啊好啊";
    }

    @ResponseBody
    @GetMapping("/testJDBCTemplate")
    public Object testJDBCTemplate(){
        return jdbcTemplate.queryForList("select * from article");
    }
    /*@RequestMapping("/toLoginPage")
    public String toLoginPage(){
        return "login";
    }*/

    @Transactional
    @ResponseBody
    @GetMapping("/saveHoby")
    public Object saveHoby(Hoby hoby){
        Person person = Person.of("万韬", 26);
        Person savedPerson = personRepository.saveAndFlush(person);
        hoby.setBelongTo(savedPerson);

        Hoby savedHoby = hobyRepository.saveAndFlush(hoby);
        return savedHoby;
    }

    @ResponseBody
    @GetMapping("/findPerson/{id}")
    public Person findPerson(@PathVariable("id") Long id){
        return personRepository.findById(id).get();
    }

    @ResponseBody
    @GetMapping("/findPersonWithMabits/{id}")
    public Person findPersonWithMabits(@PathVariable("id") Long personId){
        Person person = personMapper.findPersonWithMybatis(personId);
        List<Hoby> hobies = hobyMapper.findHobyWithMybatisByPersonId(personId);
        person.setHobies(hobies);
        return person;
    }

    @ResponseBody
    @GetMapping("/findHoby/{id}")
    public Hoby findHoby(@PathVariable("id") Long id){
        return hobyRepository.findById(id).get();
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        if("wantao".equals(user.getName()) && "123456".equals(user.getPassword())){
            session.setAttribute("user", user);
            return "redirect:/demo/auth/list";
        }else{
            return "redirect:/login";
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

   /* @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields("name");
    }*/
}
