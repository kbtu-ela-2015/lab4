using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Mvc;
using MvcDemo.Models;

namespace MvcDemo.Controllers
{
    interface BookDao
    {
        ActionResult Create (BooksDB booksDb);
        ActionResult Edit (BooksDB booksDb);
        ActionResult Delete (int id);
    }
}
