using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using DocumentsLab9.Data;
using DocumentsLab9.Models;
using Microsoft.AspNetCore.Authorization;

namespace DocumentsLab9.Controllers
{
    public class DocumentsController : Controller
    {
        private readonly ApplicationDbContext _context;

        public DocumentsController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: Documents
        [Authorize]
        public async Task<IActionResult> Index()
        {
              return _context.Document != null ? 
                          View(await _context.Document.ToListAsync()) :
                          Problem("Entity set 'ApplicationDbContext.Document'  is null.");
        }

        // GET: Documents/ShowSearchForm
        [Authorize]
        public async Task<IActionResult> ShowSearchForm(string SearchType, string SearchFormat, string PreviousFilter)
        {
            IQueryable<Document> query = _context.Document;

            if (!string.IsNullOrEmpty(SearchType))
            {
                query = query.Where(d => d.Type.Equals(SearchType));
            }

            if (!string.IsNullOrEmpty(SearchFormat))
            {
                query = query.Where(d => d.Format.Equals(SearchFormat));
            }

            var searchResults = await query.ToListAsync();

            // Pass the search results and search filters to the view
            ViewBag.SearchResults = searchResults;
            ViewBag.SearchType = SearchType;
            ViewBag.SearchFormat = SearchFormat;

            // Store the last used filter in ViewBag
            if (!string.IsNullOrEmpty(SearchType) && !string.IsNullOrEmpty(SearchFormat))
            {
                ViewBag.LastUsedFilter = $"Type: {SearchType}, Format: {SearchFormat}";
            }
            else if (!string.IsNullOrEmpty(SearchType))
            {
                ViewBag.LastUsedFilter = $"Type: {SearchType}";
            }
            else if (!string.IsNullOrEmpty(SearchFormat))
            {
                ViewBag.LastUsedFilter = $"Format: {SearchFormat}";
            }
            else
            {
                ViewBag.LastUsedFilter = string.Empty;
            }

            // Pass the previous filter to the view
            ViewBag.PreviousFilter = PreviousFilter;

            return View(searchResults);
        }





        // POST: Documents/ShowSearchResults
        [Authorize]
        public async Task<IActionResult> ShowSearchResults(String SearchType, String SearchFormat)
        {
            IQueryable<Document> query = _context.Document;

            if (!string.IsNullOrEmpty(SearchType))
            {
                query = query.Where(d => d.Type.Contains(SearchType));
            }

            if (!string.IsNullOrEmpty(SearchFormat))
            {
                query = query.Where(d => d.Format.Contains(SearchFormat));
            }

            var searchResults = await query.ToListAsync();

            return View("Index", searchResults);
        }

        // GET: Documents/Details/5
        [Authorize]
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null || _context.Document == null)
            {
                return NotFound();
            }

            var document = await _context.Document
                .FirstOrDefaultAsync(m => m.Id == id);
            if (document == null)
            {
                return NotFound();
            }

            return View(document);
        }

        // GET: Documents/Create
        [Authorize]
        public IActionResult Create()
        {
            return View();
        }

        // POST: Documents/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Author,Title,Pages,Type,Format")] Document document)
        {
            if (ModelState.IsValid)
            {
                _context.Add(document);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(document);
        }

        // GET: Documents/Edit/5
        [Authorize]
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null || _context.Document == null)
            {
                return NotFound();
            }

            var document = await _context.Document.FindAsync(id);
            if (document == null)
            {
                return NotFound();
            }
            return View(document);
        }

        // POST: Documents/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Author,Title,Pages,Type,Format")] Document document)
        {
            if (id != document.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(document);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!DocumentExists(document.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(document);
        }

        // GET: Documents/Delete/5
        [Authorize]
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null || _context.Document == null)
            {
                return NotFound();
            }

            var document = await _context.Document
                .FirstOrDefaultAsync(m => m.Id == id);
            if (document == null)
            {
                return NotFound();
            }

            return View(document);
        }

        // POST: Documents/Delete/5
        [Authorize]
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (_context.Document == null)
            {
                return Problem("Entity set 'ApplicationDbContext.Document'  is null.");
            }
            var document = await _context.Document.FindAsync(id);
            if (document != null)
            {
                _context.Document.Remove(document);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool DocumentExists(int id)
        {
          return (_context.Document?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
