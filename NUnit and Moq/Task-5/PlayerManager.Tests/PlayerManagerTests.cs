using System;
using NUnit.Framework;
using Moq;
using PlayersManagerLib;

namespace PlayerManager.Tests
{
    [TestFixture]
    public class PlayerManagerTests
    {
        private Mock<IPlayerMapper> _mockPlayerMapper;

        [OneTimeSetUp]
        public void Init()
        {
            _mockPlayerMapper = new Mock<IPlayerMapper>();
            // Ensure IsPlayerNameExistsInDb returns false so registration succeeds
            _mockPlayerMapper
                .Setup(m => m.IsPlayerNameExistsInDb(It.IsAny<string>()))
                .Returns(false);
        }

        [TestCase("Sachin")]
        public void RegisterNewPlayer_ValidName_ReturnsPlayerWithExpectedAttributes(string name)
        {
            Player player = Player.RegisterNewPlayer(name, _mockPlayerMapper.Object);

            Assert.That(player, Is.Not.Null);
            Assert.That(player.Name, Is.EqualTo(name));
            Assert.That(player.Age, Is.EqualTo(23));
            Assert.That(player.Country, Is.EqualTo("India"));
            Assert.That(player.NoOfMatches, Is.EqualTo(30));

            // Verify AddNewPlayerIntoDb was called exactly once with the given name
            _mockPlayerMapper.Verify(m => m.AddNewPlayerIntoDb(name), Times.Once);
        }

        [TestCase("")]
        public void RegisterNewPlayer_EmptyName_ThrowsArgumentException(string name)
        {
            Assert.Throws<ArgumentException>(() =>
                Player.RegisterNewPlayer(name, _mockPlayerMapper.Object));
        }

        [TestCase("ExistingPlayer")]
        public void RegisterNewPlayer_NameAlreadyExists_ThrowsArgumentException(string name)
        {
            _mockPlayerMapper
                .Setup(m => m.IsPlayerNameExistsInDb(name))
                .Returns(true);

            Assert.Throws<ArgumentException>(() =>
                Player.RegisterNewPlayer(name, _mockPlayerMapper.Object));
        }
    }
}
