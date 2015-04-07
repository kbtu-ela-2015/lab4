using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace MvcDemo.Models
{
    public class BooksDB
    {
        // key values for db
        public int ID { get; set; }
        public string Title { get; set; }
        public string Author { get; set; }
        public DateTime Date { get; set; }
    }

    public class BookDBContext : DbContext
    {
        public DbSet<BooksDB> Books { get; set; }
    }

}