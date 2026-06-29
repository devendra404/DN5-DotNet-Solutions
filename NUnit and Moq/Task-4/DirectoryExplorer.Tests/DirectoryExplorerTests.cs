using System.Collections.Generic;
using NUnit.Framework;
using Moq;
using MagicFilesLib;

namespace DirectoryExplorer.Tests
{
    [TestFixture]
    public class DirectoryExplorerTests
    {
        private readonly string _file1 = "file.txt";
        private readonly string _file2 = "file2.txt";

        private Mock<IDirectoryExplorer> _mockExplorer;

        [OneTimeSetUp]
        public void Init()
        {
            _mockExplorer = new Mock<IDirectoryExplorer>();
            _mockExplorer
                .Setup(e => e.GetFiles(It.IsAny<string>()))
                .Returns(new List<string> { "file.txt", "file2.txt" });
        }

        [TestCase]
        public void GetFiles_ReturnsExpectedFileCollection()
        {
            var result = _mockExplorer.Object.GetFiles(@"C:\SomePath");

            Assert.That(result, Is.Not.Null);
            Assert.That(result.Count, Is.EqualTo(2));
            Assert.That(result, Does.Contain(_file1));
        }
    }
}
