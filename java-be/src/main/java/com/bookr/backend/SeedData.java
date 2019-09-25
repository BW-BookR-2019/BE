package com.bookr.backend;

import com.bookr.backend.models.*;
import com.bookr.backend.services.BookService;
import com.bookr.backend.services.RoleService;
import com.bookr.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getUseremails()
          .add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1, "admin@mymail.local"));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        u2.getUseremails()
          .add(new Useremail(u2, "cinnamon@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "hops@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "bunny@email.local"));
        userService.save(u2);

//(String username, String password, List<UserRoles> userroles, List<Review> reviews, List<Book> books, List<Useremail> useremails)

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("Admin", "1234abcd", users);
        u3.getUseremails()
          .add(new Useremail(u3, "admin@admin.com"));
//        Review rv1 = new Review("I hate Twilight", u3, 1);
//        u3.getReviews()
//                .add(rv1);
//        u3.getBooks()
//                .add(new Book("Twilight","Stephanie Meyers","Puffin House", (List<Review>) rv1));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

//        ArrayList<Review> b1Review = new ArrayList<>();
//        Book b1 = new Book("Twilight","Stephanie Meyers","Puffin House");
//        Review rv1 = new Review("I hate Twilight", u3, 1);
//        b1Review.add(rv1);
//        bookService.save(b1);

    }
}