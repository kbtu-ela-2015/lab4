using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MvcDemo.Models;

namespace MvcDemo.Controllers
{ 
    public class BooksController : Controller, BookDao
    {
        private BookDBContext db = new BookDBContext();

        //
        // GET: /Books/

        public ViewResult Index()
        {
            return View(db.Books.ToList());
        }
        // trans view templ, wrapping cshtml with csharp together
        public ViewResult Somecrap()
        {
            return View(db.Books.ToList());
        }

        //
        // GET: /Books/Details/5

        public ViewResult Details(int id)
        {
            BooksDB bookdb = db.Books.Find(id);
            return View(bookdb);
        }

        //
        // GET: /Books/Create

        public ActionResult Create()
        {
            return View();
        } 

        //
        // POST: /Books/Create

        [HttpPost]
        public ActionResult Create(BooksDB bookdb)
        {
            if (ModelState.IsValid)
            {
                db.Books.Add(bookdb);
                db.SaveChanges();
                return RedirectToAction("Index");  
            }

            return View(bookdb);
        }
        
        //
        // GET: /Books/Edit/5
 
        public ActionResult Edit(int id)
        {
            BooksDB bookdb = db.Books.Find(id);
            return View(bookdb);
        }

        //
        // POST: /Books/Edit/5

        [HttpPost]
        public ActionResult Edit(BooksDB bookdb)
        {
            if (ModelState.IsValid)
            {
                db.Entry(bookdb).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(bookdb);
        }

        //
        // GET: /Books/Delete/5
 
        public ActionResult Delete(int id)
        {
            BooksDB bookdb = db.Books.Find(id);
            return View(bookdb);
        }

        //
        // POST: /Books/Delete/5

        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(int id)
        {            
            BooksDB bookdb = db.Books.Find(id);
            db.Books.Remove(bookdb);
            db.SaveChanges();
            return RedirectToAction("Index");
        }
        // sending Dbcontext to database.sdf**
        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
    }
}