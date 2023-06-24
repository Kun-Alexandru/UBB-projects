namespace DocumentsLab9.Models
{
    public class Document
    {
        public int Id { get; set; }
        public string Author { get; set; }
        public string Title { get; set; }
        public int Pages { get; set; }
        public string Type { get; set; }
        public string Format { get; set; }
    }
}
